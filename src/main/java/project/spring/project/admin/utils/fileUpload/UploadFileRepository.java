package project.spring.project.admin.utils.fileUpload;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UploadFileRepository  extends JpaRepository<UploadFileEntity, Long> {
    Page<UploadFileEntity> findByProductId(Long productId,Pageable pageable);
    Optional<UploadFileEntity> findByIdAndProductId(Long id, Long productId);
}
