package andrew.backend.app.domain.main.account.service;

import andrew.backend.app.domain.main.account.model.entity.CountryCodeEntity;
import andrew.backend.app.domain.main.account.model.entity.LanguageEntity;

public interface LanguageService {
    LanguageEntity countryToLanguage(long codeId);
    CountryCodeEntity languageToCountry(String languageCode);
}
