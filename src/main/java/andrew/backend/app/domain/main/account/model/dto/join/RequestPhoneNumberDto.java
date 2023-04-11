package andrew.backend.app.domain.main.account.model.dto.join;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RequestPhoneNumberDto {
    @Schema(description = "국가번호")
    String countryCode;
    @Schema(description = "전화번호")
    String phoneNumber;

}
