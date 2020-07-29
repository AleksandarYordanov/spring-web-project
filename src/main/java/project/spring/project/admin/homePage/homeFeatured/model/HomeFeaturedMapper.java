package project.spring.project.admin.homePage.homeFeatured.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import project.spring.project.admin.product.model.ProductChildDTO;
import project.spring.project.admin.product.model.ProductDTO;
import project.spring.project.admin.product.model.ProductEntity;
import project.spring.project.admin.product.model.ProductSelfDTO;
import project.spring.project.admin.utils.fileUpload.UploadFileEntity;
import project.spring.project.admin.utils.fileUpload.UploadFileSelfDTO;

import java.util.List;

@Mapper
public interface HomeFeaturedMapper {

    HomeFeaturedMapper INSTANCE = Mappers.getMapper(HomeFeaturedMapper.class);

    //productChildDTO
    List<ProductEntity> mapProductsDtoListToEntity(List<ProductChildDTO> products);

    List<ProductChildDTO> mapProductsEntityListToDto(List<ProductEntity> products);

    ProductEntity mapProductDtoToEntity(ProductChildDTO category);

    ProductChildDTO mapProductEntityToDto(ProductEntity category);

    //productSelfDTO
    ProductEntity mapProductSelfDtoToEntity(ProductSelfDTO dto);

    ProductSelfDTO mapProductEntityToProductSelfDTO(ProductEntity entity);

    List<ProductEntity> mapProductsSelfDtoListToEntity(List<ProductSelfDTO> products);

    List<ProductSelfDTO> mapProductsEntityListToProductSelfDto(List<ProductEntity> products);

    HomeFeaturedEntity mapChildDTOtoEntity(HomeFeaturedChildDTO dto);
    HomeFeaturedChildDTO mapEntityToChildDTO(HomeFeaturedEntity entity);

    UploadFileEntity mapUploadFileSelfDTOToEntity(UploadFileSelfDTO dto);
    UploadFileSelfDTO mapUploadFileEntityToSelfDTO(UploadFileEntity entity);

}
