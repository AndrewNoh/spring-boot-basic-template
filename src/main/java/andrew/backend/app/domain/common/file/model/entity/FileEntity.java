package andrew.backend.app.domain.common.file.model.entity;

import andrew.backend.app.global.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FileEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileId;

    private String mimeType;

    private Long size;

    private String filename;

    private String s3name;

    private String saveName;

    private Boolean isDeleted;


}
