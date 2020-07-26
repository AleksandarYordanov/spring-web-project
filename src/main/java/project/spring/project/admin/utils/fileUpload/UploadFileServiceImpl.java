package project.spring.project.admin.utils.fileUpload;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import project.spring.project.admin.product.model.ProductMapper;
import project.spring.project.admin.product.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UploadFileServiceImpl implements UploadFileService {
    private final UploadFileRepository uploadFileRepository;
    private final ProductRepository productRepository;

    public UploadFileServiceImpl(UploadFileRepository uploadFileRepository, ProductRepository productRepository) {
        this.uploadFileRepository = uploadFileRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<UploadFileDTO> getAll() {
        return uploadFileRepository.
                findAll().
                stream().
                map(UploadFileMapper.INSTANCE::mapUploadFileEntityToDto).
                collect(Collectors.toList());
    }

    @Override
    public List<UploadFileSelfDTO> getAllPhotosForProduct(Long productId) {
        return uploadFileRepository
                .findByProductId(productId, PageRequest.of(0, 2)

                )
                .stream()
                .map(UploadFileMapper.INSTANCE::mapUploadFileEntityToSelfDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UploadFileDTO getFile(Long id) {
      UploadFileEntity uploadFileEntity = uploadFileRepository.getOne(id);
      return UploadFileMapper.INSTANCE.mapUploadFileEntityToDto(uploadFileEntity);
    }

    @Override
    public void saveFile(UploadFileDTO uploadedFile) {
     uploadFileRepository.save(UploadFileMapper.INSTANCE.mapUploadFileDtoToEntity(uploadedFile));

    }

    @Override
    public void saveFileForProductId(UploadFileDTO uploadFileDTO, Long productId) {
        productRepository.findById(productId).map(product-> {

            uploadFileDTO.setProduct(ProductMapper.INSTANCE.mapProductEntityToDto(product));
            return uploadFileRepository.save(UploadFileMapper.INSTANCE.mapUploadFileDtoToEntity(uploadFileDTO));
        }).orElseThrow(() ->  new IllegalArgumentException("no such product with id: " + productId));
    }

    @Override
    public void deleteFileWithId(Long id) {

    }
}
