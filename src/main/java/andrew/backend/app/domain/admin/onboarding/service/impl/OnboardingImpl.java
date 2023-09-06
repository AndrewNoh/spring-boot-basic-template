package andrew.backend.app.domain.admin.onboarding.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import andrew.backend.app.domain.admin.onboarding.model.UpAndDown;
import andrew.backend.app.domain.admin.onboarding.model.dto.*;
import andrew.backend.app.domain.admin.onboarding.model.entity.AdminOnboardingEntity;
import andrew.backend.app.domain.admin.onboarding.model.repository.AdminOnboardingRepo;
import andrew.backend.app.domain.admin.onboarding.service.OnboardingService;
import andrew.backend.app.domain.common.file.model.repository.FileRepo;
import andrew.backend.app.domain.main.account.model.entity.CountryCodeEntity;
import andrew.backend.app.domain.main.account.model.repository.CountryCodeRepo;
import andrew.backend.app.domain.main.account.service.LanguageService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OnboardingImpl implements OnboardingService {
    private final AdminOnboardingRepo onboardingRepo;
    private final LanguageService languageService;
    private final CountryCodeRepo countryCodeRepo;
    private final FileRepo fileRepo;
    private final ModelMapper modelMapper;

    // 온보딩 등록
    @Override
    public ResponseOnboardingDto writeOnboarding(RequestWriteOnboardingDto request) {
        CountryCodeEntity countryCode = findCountryCodeById(request.getCodeId());
        int nextSequence = getNextSequence(request.getCodeId());
        AdminOnboardingEntity onboarding = createOnboardingEntity(countryCode, nextSequence);
        onboardingRepo.save(onboarding);
        return modelMapper.map(onboarding, ResponseOnboardingDto.class);
    }

    // 언어코드에 따른 온보딩 조회
    @Override
    public List<ResponseOnboardingDto> showOnboarding(String languageCode) {
        CountryCodeEntity countryCodeEntity = languageService.languageToCountry(languageCode);
        List<AdminOnboardingEntity> onboardingEntityList = onboardingRepo.findByCountryCodeAndStatusTrueOrderBySequence(countryCodeEntity);
        if (onboardingEntityList.isEmpty()) return Collections.emptyList();
        return onboardingEntityList.stream().map(onboarding -> modelMapper.map(onboarding, ResponseOnboardingDto.class)).collect(Collectors.toList());

    }

    // 온보딩 수정
    @Override
    public boolean modifyOnboarding(OnboardingWriteDto dto) {
        AdminOnboardingEntity onboardingEntity = onboardingRepo.findById(dto.getOnId()).orElseThrow(() -> new EntityNotFoundException("Onboarding Not Found"));

        switch (dto.getOnType()) {
            case TITLE:
                onboardingEntity.setTitle(((RequestOnboardingTitleDto) dto).getTitle());
                break;
            case IMAGE:
                onboardingEntity.setFile(fileRepo.findById(((RequestOnboardingImageDto) dto).getFileId()).orElseThrow(() -> new EntityNotFoundException("OnboardingImg Not Found")));
                break;
            case STATUS:
                onboardingEntity.setStatus(((RequestOnboardingStatusDto) dto).getActivate());
                break;
        }

        onboardingEntity = onboardingRepo.save(onboardingEntity);
        return onboardingEntity.getId() != null;
    }

    // 시퀀스 변경
    @Override
    public boolean moveOnboarding(RequestOnboardingMoveDto dto) {
        CountryCodeEntity countryCodeEntity = findCountryCodeById(dto.getCodeId());
        Optional<AdminOnboardingEntity> target = onboardingRepo.findById(dto.getOnId());
        if (target.isEmpty()) {
            throw new EntityNotFoundException("Onboarding Not Found");
        }

        List<AdminOnboardingEntity> onboardingList = onboardingRepo.findByCountryCodeAndStatusTrueOrderBySequence(countryCodeEntity);

        int currentIndex = onboardingList.indexOf(target.get());
        if (currentIndex == -1) {
            return false;
        }

        int targetIndex = dto.getDirection().equals(UpAndDown.UP) ? currentIndex - 1 : currentIndex + 1;
        if (targetIndex < 0 || targetIndex >= onboardingList.size()) {
            return false;
        }

        AdminOnboardingEntity currentOnboarding = onboardingList.get(currentIndex);
        AdminOnboardingEntity targetOnboarding = onboardingList.get(targetIndex);

        int currentSequence = currentOnboarding.getSequence();
        currentOnboarding.setSequence(targetOnboarding.getSequence());
        targetOnboarding.setSequence(currentSequence);

        onboardingRepo.save(currentOnboarding);
        onboardingRepo.save(targetOnboarding);

        return true;
    }

    private CountryCodeEntity findCountryCodeById(Long codeId) {
        return countryCodeRepo.findById(codeId).orElseThrow(() -> new CountryCodeNotFoundException("Country code not found for id: " + codeId));
    }

    private int getNextSequence(Long codeId) {
        List<Integer> lastSequences = onboardingRepo.findLastSequenceByCountryCodeId(codeId, PageRequest.of(0, 1));
        int lastSequence = lastSequences.isEmpty() ? 0 : lastSequences.get(0);
        return lastSequence + 1;
    }

    private AdminOnboardingEntity createOnboardingEntity(CountryCodeEntity countryCode, int sequence) {
        AdminOnboardingEntity onboarding = new AdminOnboardingEntity();
        onboarding.setCountryCode(countryCode);
        onboarding.setSequence(sequence);
        onboarding.setStatus(true);
        return onboarding;
    }

}
