package project.spring.project.eCommerce.cart.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import project.spring.project.admin.product.model.ProductDTO;
import project.spring.project.admin.product.model.ProductEntity;
import project.spring.project.admin.product.model.ProductSelfDTO;


import java.util.List;

@Mapper
public interface CartMapper {

    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

    //productDTO
    List<ProductEntity> mapProductsDtoListToEntity(List<ProductDTO> products);

    List<ProductDTO> mapProductsEntityListToDto(List<ProductEntity> products);

    ProductEntity mapProductDtoToEntity(ProductDTO category);

    ProductDTO mapProductEntityToDto(ProductEntity category);

    //productSelfDTO
    ProductEntity mapProductSelfDtoToEntity(ProductSelfDTO dto);

    ProductSelfDTO mapProductEntityToProductSelfDTO(ProductEntity entity);

    List<ProductEntity> mapProductsSelfDtoListToEntity(List<ProductSelfDTO> products);

    List<ProductSelfDTO> mapProductsEntityListToProductSelfDto(List<ProductEntity> products);







    CartChildDTO mapCartEntityToCartChildDTO(CartEntity entity);


    CartEntity mapCartSelfDTOToCartEntity(CartChildDTO cartChildDTO);
}
