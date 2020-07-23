package project.spring.project.admin.product.model;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductEntity mapProductDtoToEntity(ProductDTO dto);

    ProductDTO mapProductEntityToDto(ProductEntity entity);

}
