package andrew.backend.app.domain.main.account.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import andrew.backend.app.domain.main.account.model.entity.CountryCodeEntity;
import andrew.backend.app.domain.main.account.model.entity.LanguageEntity;
import andrew.backend.app.domain.main.account.model.repository.CountryCodeRepo;
import andrew.backend.app.domain.main.account.model.repository.LanguageRepo;
import andrew.backend.app.domain.main.account.service.LanguageService;

import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class LanguageImpl implements LanguageService {
    private final LanguageRepo languageRepo;
    private final CountryCodeRepo countryCodeRepo;

    private static final Map<String, Long> LANGUAGE_COUNTRY_MAP = Map.of(
            "ko", 1L,
            "vi", 4L,
            "th", 8L,
            "ph", 9L);

    @Override
    public LanguageEntity countryToLanguage(long codeId) {
        String languageCode = LANGUAGE_COUNTRY_MAP.entrySet().stream().filter(entry -> entry.getValue().equals(codeId)).map(Map.Entry::getKey).findFirst().orElse("en");
        return languageRepo.findByLanguageCode(languageCode);
    }

    @Override
    public CountryCodeEntity languageToCountry(String languageCode) {
        Long codeId = LANGUAGE_COUNTRY_MAP.get(languageCode);
        return countryCodeRepo.findById(codeId).orElseThrow(CountryCodeNotFoundException::new);
    }
}