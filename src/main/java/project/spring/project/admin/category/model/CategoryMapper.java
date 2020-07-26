package project.spring.project.admin.category.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import project.spring.project.admin.department.model.DepartmentDTO;
import project.spring.project.admin.department.model.DepartmentEntity;
import project.spring.project.admin.type.model.TypeDTO;
import project.spring.project.admin.type.model.TypeEntity;
import project.spring.project.admin.type.model.TypeSelfDTO;

import java.util.List;


@Mapper
public interface CategoryMapper  {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    //type
   List<TypeEntity> mapTypeDtoListToEntity(List<TypeDTO> dto);

   List<TypeDTO> mapTypeEntityListToDto(List<TypeEntity> entity);

    TypeEntity mapTypeDtoToEntity(TypeDTO typeDTO);

    TypeDTO mapTypeEntityToDto(TypeEntity typeEntity);

    //typeSelf
    List<TypeEntity> mapTypeDtoSelfListToEntity(List<TypeSelfDTO> dto);

    List<TypeSelfDTO> mapTypeEntityListToSelfDto(List<TypeEntity> entity);

    TypeEntity mapTypeDtoToEntity(TypeSelfDTO typeDTO);

    TypeSelfDTO mapTypeEntityToSelfDto(TypeEntity typeEntity);

    //category
    CategoryEntity mapCategoryDtoToEntity(CategoryDTO dto);

    CategoryDTO mapCategoryEntityToDto(CategoryEntity entity);

    //categoryChild
    CategoryEntity mapCategoryChildDtoToEntity(CategoryChildDTO dto);

    CategoryChildDTO mapCategoryEntityToChildDto(CategoryEntity entity);

    //categorySelf
    CategoryEntity mapCategorySelfDtoToEntity(CategorySelfDTO dto);

    CategorySelfDTO mapCategoryEntityToSelfDto(CategoryEntity entity);
}
