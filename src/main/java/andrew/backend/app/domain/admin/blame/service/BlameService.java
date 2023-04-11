package andrew.backend.app.domain.admin.blame.service;


import andrew.backend.app.domain.admin.blame.model.dto.RequestBlameDto;
import andrew.backend.app.domain.main.account.model.entity.UserInfoEntity;

public interface BlameService {
    boolean addBlame(RequestBlameDto req, UserInfoEntity userInfo);
}
