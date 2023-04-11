package andrew.backend.app.domain.common.file.model.enums;

import lombok.Getter;

@Getter
public enum FileExtensionType {
    IMAGE("이미지"),
    VIDEO("비디오"),
    ETC("기타");

    private final String value;

    FileExtensionType(String value) {
        this.value = value;
    }
}
