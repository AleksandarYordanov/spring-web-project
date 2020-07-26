package project.spring.project.admin.utils.fileUpload;

import java.util.List;

public interface UploadFileService {

    List<UploadFileDTO> getAll();
    List<UploadFileSelfDTO> getAllPhotosForProduct(Long productId);

    UploadFileDTO getFile(Long id);

    void saveFile(UploadFileDTO uploadedFile);
    void saveFileForProductId(UploadFileDTO uploadFileDTO,Long productId);
    void deleteFileWithId(Long id);
}
