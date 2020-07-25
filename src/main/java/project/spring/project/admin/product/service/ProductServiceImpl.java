package project.spring.project.admin.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.spring.project.admin.product.model.ProductDTO;
import project.spring.project.admin.product.model.ProductEntity;
import project.spring.project.admin.product.model.ProductMapper;
import project.spring.project.admin.product.repository.ProductRepository;
import project.spring.project.admin.utils.fileUpload.UploadFileDTO;
import project.spring.project.admin.utils.fileUpload.UploadFileEntity;
import project.spring.project.admin.utils.fileUpload.UploadFileService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final UploadFileService uploadFileService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, UploadFileService uploadFileService) {
        this.productRepository = productRepository;
        this.uploadFileService = uploadFileService;
    }


    @Override
    public List<ProductDTO> getAll() {



        List<ProductDTO> productDTOList = productRepository.
                findAll().
                stream().
                map(ProductMapper.INSTANCE::mapProductEntityToDto).
                collect(Collectors.toList());

        productDTOList.forEach(p -> {
          p.setPhotos( uploadFileService.getAllPhotosForProduct(p.getId()));
        });

        return productDTOList;
    }

    @Override
    public void create(ProductDTO productDTO) {
        ProductEntity productEntity = ProductMapper.INSTANCE.mapProductDtoToEntity(productDTO);
        productRepository.save(productEntity);
    }

    @Override
    public Long createAndReturnId(ProductDTO productDTO) {
        ProductEntity productEntity = ProductMapper.INSTANCE.mapProductDtoToEntity(productDTO);
       productEntity = productRepository.save(productEntity);
       return productEntity.getId();
    }

    @Override
    public ProductDTO getById(Long id) {
      Optional<ProductEntity> productEntity = productRepository.findById(id);
      if (productEntity.isPresent()){
          return  ProductMapper.INSTANCE.mapProductEntityToDto(productEntity.get());
      }else {
          throw new IllegalArgumentException("no such entity with id: " + id);
      }
    }

    @Override
    public void addPhotoToId(UploadFileDTO photo, Long productId) {
      uploadFileService.saveFileForProductId(photo,productId);
    }
}
