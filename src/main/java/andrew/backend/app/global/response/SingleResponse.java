package andrew.backend.app.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class SingleResponse<T> extends CommonResponse {
    private T data;
}
