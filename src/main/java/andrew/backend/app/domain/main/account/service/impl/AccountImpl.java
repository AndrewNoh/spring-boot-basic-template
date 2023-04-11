package andrew.backend.app.domain.main.account.service.impl;

import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import andrew.backend.app.domain.admin.term.model.entity.AdminTermConditionsEntity;
import andrew.backend.app.domain.admin.term.model.repository.TermConditionsRepo;
import andrew.backend.app.domain.main.account.model.dto.UserEntityRoleDto;
import andrew.backend.app.domain.main.account.model.dto.join.*;
import andrew.backend.app.domain.main.account.model.dto.join.TermsCountryCodeDto.CountryCodeDto;
import andrew.backend.app.domain.main.account.model.dto.join.TermsCountryCodeDto.TermConditionsDto;
import andrew.backend.app.domain.main.account.model.entity.*;
import andrew.backend.app.domain.main.account.model.enums.Roles;
import andrew.backend.app.domain.main.account.model.enums.SnsProvider;
import andrew.backend.app.domain.main.account.model.repository.*;
import andrew.backend.app.domain.main.account.service.AccountService;
import andrew.backend.app.domain.main.account.service.LanguageService;
import andrew.backend.app.global.exception.custom.CountryCodeNotFoundException;
import andrew.backend.app.global.exception.custom.DuplicatedEmailException;
import andrew.backend.app.global.exception.custom.NotFoundEmailException;
import andrew.backend.app.global.security.sns.SnsTokenVerifier;
import andrew.backend.app.global.util.CertificationRandomNumber;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountImpl implements AccountService {

    private final UserInfoRepo userInfoRepo;
    private final CountryCodeRepo countryCodeRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ModelMapper modelMapper;
    private final LanguageService languageService;
    private final RoleRepo roleRepo;
    private final RolesUserRepo rolesUserRepo;
    private final TermConditionsRepo termConditionsRepo;
    private final ReferralRepo referralRepo;
    private final UserTermRepo userTermRepo;
    private final SnsTokenVerifier snsTokenVerifier;
    private final SnsInfoRepo snsInfoRepo;
    @Value("${jwt.secret.key}")
    private String SECRET;

    private UserInfoEntity buildUserInfoEntity(UserJoinDto userJoinDto) {
        CountryCodeEntity countryCodeEntity = countryCodeRepo.findById(userJoinDto.getCode_id()).orElseThrow(CountryCodeNotFoundException::new);
        UserInfoEntity userInfo = new UserInfoEntity();
        userInfo.setEmail(userJoinDto.getEmail());
        userInfo.setPhoneNumber(userJoinDto.getPhone_number());
        userInfo.setCountryCode(countryCodeEntity);
        userInfo.setLanguage(languageService.countryToLanguage(userJoinDto.getCode_id()));
        userInfo.setNickname(userJoinDto.getNickName());
        addRole(userInfo, Roles.ROLE_USER);
        addTerm(userInfo, userJoinDto.getTermsAgreeDtoList());
        createReferral(userInfo);
        if (userJoinDto instanceof SocialJoinDto) {
            joinSocial(userInfo, (SocialJoinDto) userJoinDto);
            userInfo.setPassword(bCryptPasswordEncoder.encode(SECRET));
        } else {
            userInfo.setPassword(bCryptPasswordEncoder.encode(((NormalJoinDto) userJoinDto).getPassword()));
        }
        return userInfo;
    }

    //일반 회원 가입
    @Override
    public boolean normalJoin(NormalJoinDto normalJoinDto) {
        try {
            UserInfoEntity userInfo = buildUserInfoEntity(normalJoinDto);
            userInfo.setJoinType(SnsProvider.NORMAL);
            userInfoRepo.save(userInfo);
            if (normalJoinDto.getReferralCode() != null) enterReferralCode(normalJoinDto.getReferralCode());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //소셜 회원가입
    @Override
    public boolean socialJoin(SocialJoinDto socialJoinDto) {
        Optional<UserInfoEntity> user = findByEmail(socialJoinDto.getEmail());
        if (user.isPresent()) throw new DuplicatedEmailException("해당 소셜 이메일로 이미 가입이 되어있습니다.");
        try {
            UserInfoEntity userInfo = buildUserInfoEntity(socialJoinDto);
            userInfo.setJoinType(socialJoinDto.getProvider());
            userInfoRepo.save(userInfo);
            if (socialJoinDto.getReferralCode() != null) enterReferralCode(socialJoinDto.getReferralCode());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Optional<UserInfoEntity> findByEmail(String email) {
        return userInfoRepo.findByEmail(email);
    }

    @Override
    public UserEntityRoleDto getRolesUserEntity(String email) {
        UserInfoEntity userInfo = userInfoRepo.findByEmail(email).orElseThrow(NotFoundEmailException::new);
        UserEntityRoleDto userEntityRoleDto = new UserEntityRoleDto();
        userEntityRoleDto.setUserInfo(userInfo);
        userEntityRoleDto.setRoles(userInfoRepo.findRoleNamesByUsername(userInfo.getUserId()));
        return userEntityRoleDto;
    }

    @Override
    public TermsCountryCodeDto callDefault(String languageCode) {
        CountryCodeEntity countryCodeEntity = languageService.languageToCountry(languageCode);
        List<AdminTermConditionsEntity> conditionsEntityList = termConditionsRepo.findByCountryCode(countryCodeEntity);
        List<CountryCodeDto> countryCodeDtoList = modelMapper.map(countryCodeRepo.findAll(), new TypeToken<List<CountryCodeDto>>() {
        }.getType());
        List<TermConditionsDto> termConditionsDtoList = modelMapper.map(conditionsEntityList, new TypeToken<List<TermConditionsDto>>() {
        }.getType());

        return new TermsCountryCodeDto(termConditionsDtoList, countryCodeDtoList);
    }


    private void addRole(UserInfoEntity userInfo, Roles roles) {
        RoleEntity role = roleRepo.findByName(roles);
        if (role == null) throw new RuntimeException("Role not found");
        RolesUserEntity rolesUser = new RolesUserEntity();
        rolesUser.setUserInfo(userInfo);
        rolesUser.setRoleEntity(role);
        rolesUserRepo.save(rolesUser);
    }

    private void addTerm(UserInfoEntity userInfo, List<TermsAgreeDto> termsAgreeDtoList) {
        List<UserTermEntity> userTermEntityList = new ArrayList<>();
        for (TermsAgreeDto termsAgreeDto : termsAgreeDtoList) {
            UserTermEntity userTermEntity = new UserTermEntity();
            userTermEntity.setTermConditionById(termsAgreeDto.getTermsId(), termConditionsRepo);
            userTermEntity.setUserInfo(userInfo);
            userTermEntity.setIsAgree(termsAgreeDto.isAgree());
            userTermEntity.setAgreeDate(LocalDateTime.now());
            userTermEntityList.add(userTermEntity);
        }
        userTermRepo.saveAll(userTermEntityList);
    }

    private void createReferral(UserInfoEntity userInfo) {
        ReferralEntity referralEntity = new ReferralEntity();
        referralEntity.setCnt(0);
        referralEntity.setCode(CertificationRandomNumber.random());
        referralEntity.setUserInfo(userInfo);
        referralRepo.save(referralEntity);
    }

    private void enterReferralCode(String referralCode) {
        ReferralEntity referral = referralRepo.findByCode(referralCode);
        referral.setCnt(referral.getCnt() + 1);
        referralRepo.save(referral);
    }

    private void joinSocial(UserInfoEntity userInfo, SocialJoinDto socialJoinDto) {
        SnsInfoEntity snsInfo = new SnsInfoEntity();
        snsInfo.setSnsEmail(socialJoinDto.getEmail());
        snsInfo.setProvider(socialJoinDto.getProvider());
        snsInfo.setUserInfo(userInfo);
        snsInfo.setAccessId(snsTokenVerifier.getSnsIdFromToken(socialJoinDto.getProvider(), socialJoinDto.getAccessToken()));
        snsInfoRepo.save(snsInfo);
    }

}
