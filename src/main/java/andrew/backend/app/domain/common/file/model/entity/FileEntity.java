package andrew.backend.app.domain.common.file.model.entity;

import andrew.backend.app.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "file", schema = "andrew")
public class FileEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long fileId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private FileEntity parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<FileEntity> children = new ArrayList<>();

    @Column(name = "mime_type")
    private String mimeType;

    @Column(name = "size")
    private Long size;

    @Column(name = "original_name")
    private String filename;

    @Column(name = "s3_name")
    private String s3name;

    @Column(name = "save_name")
    private String saveName;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "sequence")
    private Integer sequence = 0;

}
