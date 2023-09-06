package andrew.backend.app.domain.common.file.model.enums;

import lombok.Getter;

@Getter
public enum FileType {
    ADMIN("관리자"),
    USER("유저");

    private final String value;

    FileType(String value) {
        this.value = value;
    }
}
