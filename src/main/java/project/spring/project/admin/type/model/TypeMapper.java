package project.spring.project.admin.type.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import project.spring.project.admin.category.model.CategoryDTO;
import project.spring.project.admin.category.model.CategoryEntity;
import project.spring.project.admin.category.model.CategorySelfDTO;
import project.spring.project.admin.product.model.ProductDTO;
import project.spring.project.admin.product.model.ProductEntity;
import project.spring.project.admin.product.model.ProductSelfDTO;

import java.util.List;

@Mapper
public interface TypeMapper {

    TypeMapper INSTANCE = Mappers.getMapper(TypeMapper.class);

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


    //categoryDTO
    List<CategoryEntity> mapCategoryDtoListToEntity(List<CategoryDTO> products);

    List<CategoryDTO> mapCategoryEntityListToDto(List<CategoryEntity> products);

    CategoryEntity mapCategoryDtoToEntity(CategoryDTO category);

    CategoryDTO mapCategoryEntityToDto(CategoryEntity category);

    //categorySelfDTO
    CategoryEntity mapCategorySelfDtoToEntity(CategorySelfDTO dto);

    CategorySelfDTO mapCategoryEntityToCategorySelfDTO(CategoryEntity entity);

    List<CategoryEntity> mapCategorySelfDtoListToEntity(List<CategorySelfDTO> products);

    List<CategorySelfDTO> mapCategoryEntityListToCategorySelfDto(List<CategoryEntity> products);



    TypeEntity mapTypeDtoToEntity(TypeDTO dto);

    TypeDTO mapTypeEntityToDto(TypeEntity entity);

    TypeChildDTO mapTypeEntityToTypeChildDTO(TypeEntity entity);


    TypeEntity mapTypeSelfDTOToTypeEntity(TypeSelfDTO typeSelfDTO);
}
