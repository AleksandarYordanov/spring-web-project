package project.spring.project.admin.product.service;

import project.spring.project.admin.product.model.ProductChildDTO;
import project.spring.project.admin.product.model.ProductDTO;
import project.spring.project.admin.utils.fileUpload.UploadFileDTO;

import java.util.List;

public interface ProductService {
    List<ProductChildDTO> getAll();
    void create(ProductDTO productDTO);
    Long createAndReturnId(ProductDTO productDTO);
    ProductDTO getById(Long id);
    void addPhotoToId(UploadFileDTO photo,Long productId );

    void createProductWithImage(ProductDTO product, List<String> myParams);
}
