package project.spring.project.admin.type.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import project.spring.project.admin.category.model.CategoryDTO;
import project.spring.project.admin.category.model.CategoryEntity;
import project.spring.project.admin.product.model.ProductDTO;
import project.spring.project.admin.product.model.ProductEntity;

import java.util.List;

@Mapper
public interface TypeMapper {

    TypeMapper INSTANCE = Mappers.getMapper(TypeMapper.class);

    List<ProductEntity> mapProductsDtoListToEntity(List<ProductDTO> products);

    List<ProductDTO> mapProductsEntityListToDto(List<ProductEntity> products);

    ProductEntity mapProductDtoToEntity(ProductDTO category);

    ProductDTO mapProductEntityToDto(ProductEntity category);

    TypeEntity mapTypeDtoToEntity(TypeDTO dto);

    TypeDTO mapTypeEntityToDto(TypeEntity entity);
}
