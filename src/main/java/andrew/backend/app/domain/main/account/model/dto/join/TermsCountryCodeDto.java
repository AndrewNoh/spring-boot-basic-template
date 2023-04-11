package andrew.backend.app.domain.main.account.model.dto.join;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "TermsCountryCodeDto", description = "이용약관 및 국가코드 DTO")
public class TermsCountryCodeDto {
    @Schema(description = "이용약관 정보 리스트")
    List<TermConditionsDto> termConditionsDtoList;

    @Schema(description = "국가 코드 정보 리스트")
    List<CountryCodeDto> countryCodeDtoList;

    @Getter
    @Setter
    public static class TermConditionsDto {
        @Schema(description = "이용약관 ID")
        private Long termId;

        @Schema(description = "이용약관 제목")
        private String termsTitle;

        @Schema(description = "이용약관 내용")
        private String termsBody;

        @Schema(description = "이용약관 필수 여부")
        private Boolean isRequired;
    }

    @Getter
    @Setter
    public static class CountryCodeDto {

        @Schema(description = "국가 코드 ID")
        private Long codeId;

        @Schema(description = "국가 코드")
        private String code;

        @Schema(description = "국가명")
        private String country;
    }


}
