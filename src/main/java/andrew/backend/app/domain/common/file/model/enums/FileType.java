package andrew.backend.app.domain.common.file.model.enums;

import lombok.Getter;

@Getter
public enum FileType {
    IMAGE("이미지"),
    ETC("기타");

    private final String value;

    FileType(String value) {
        this.value = value;
    }
}
