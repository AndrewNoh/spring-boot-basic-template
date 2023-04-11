package andrew.backend.app.domain.common.file.model.repository;



import andrew.backend.app.domain.common.file.model.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepo extends JpaRepository<FileEntity, Long> {
    List<FileEntity> findByFileIdIn(List<Long> fileIdList);
}
