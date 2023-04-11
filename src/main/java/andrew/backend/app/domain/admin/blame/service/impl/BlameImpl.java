package andrew.backend.app.domain.admin.blame.service.impl;

import andrew.backend.app.domain.admin.blame.model.dto.RequestBlameDto;
import andrew.backend.app.domain.admin.blame.model.entity.AdminBlameEntity;
import andrew.backend.app.domain.admin.blame.model.enums.BlameStatus;
import andrew.backend.app.domain.admin.blame.model.repository.AdminBlameRepo;
import andrew.backend.app.domain.admin.blame.service.BlameService;
import andrew.backend.app.domain.main.account.model.entity.UserInfoEntity;
import andrew.backend.app.domain.main.account.model.repository.UserInfoRepo;
import andrew.backend.app.global.exception.custom.NotFoundEmailException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BlameImpl implements BlameService {

    private final AdminBlameRepo adminBlameRepo;
    private final UserInfoRepo userInfoRepo;

    @Override
    public boolean addBlame(RequestBlameDto req, UserInfoEntity userInfo) {
        AdminBlameEntity blame = new AdminBlameEntity();
        userInfo = userInfoRepo.findById(userInfo.getUserId()).orElseThrow(NotFoundEmailException::new);
        blame.setUserInfo(userInfo);
        blame.setCountryCode(userInfo.getCountryCode());
        blame.setTargetId(req.getTargetId());
        blame.setTargetType(req.getTargetType());
        blame.setBlameType(req.getBlameType());
        if(req.getBlameBody()!=null)
            blame.setBlameBody(req.getBlameBody());
        blame.setStatus(BlameStatus.UNCOMPLETED);
        blame = adminBlameRepo.save(blame);
        return blame.getBlameId() != null;
    }
}
