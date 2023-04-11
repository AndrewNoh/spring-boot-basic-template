package andrew.backend.app.domain.admin.onboarding.service;


import andrew.backend.app.domain.admin.onboarding.model.dto.OnboardingWriteDto;
import andrew.backend.app.domain.admin.onboarding.model.dto.RequestOnboardingMoveDto;
import andrew.backend.app.domain.admin.onboarding.model.dto.RequestWriteOnboardingDto;
import andrew.backend.app.domain.admin.onboarding.model.dto.ResponseOnboardingDto;

import java.util.List;

public interface OnboardingService {
    ResponseOnboardingDto writeOnboarding(RequestWriteOnboardingDto writeOnboardingDto);

    List<ResponseOnboardingDto> showOnboarding(String languageCode);

    boolean modifyOnboarding(OnboardingWriteDto dto);

    boolean moveOnboarding(RequestOnboardingMoveDto moveDto);
}
