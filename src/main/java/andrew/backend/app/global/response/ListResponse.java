package andrew.backend.app.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
@AllArgsConstructor
public class ListResponse<T> extends CommonResponse {
    private List<T> data;
}
