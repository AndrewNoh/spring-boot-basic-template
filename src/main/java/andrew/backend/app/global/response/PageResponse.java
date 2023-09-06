package andrew.backend.app.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;


@Setter
@Getter
@AllArgsConstructor
public class PageResponse<T> extends CommonResponse {
    private Page<T> data;
}
