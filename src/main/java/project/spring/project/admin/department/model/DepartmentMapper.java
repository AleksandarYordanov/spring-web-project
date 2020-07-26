package project.spring.project.admin.department.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import project.spring.project.admin.category.model.CategoryChildDTO;
import project.spring.project.admin.category.model.CategoryDTO;
import project.spring.project.admin.category.model.CategoryEntity;
import project.spring.project.admin.category.model.CategorySelfDTO;

import java.util.List;

@Mapper
public interface DepartmentMapper {

    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);

    //categoryDTO
    List<CategoryEntity> mapCategoriesDtoListToEntity(List<CategoryDTO> categories);
    List<CategoryDTO> mapCategoriesEntityListToDto(List<CategoryEntity> categories);
    CategoryEntity mapCategoryDtoToEntity(CategoryDTO category);
    CategoryDTO mapCategoryEntityToDto(CategoryEntity category);

    //categoryChildDTO
    List<CategoryEntity> mapCategoriesChildDtoListToEntity(List<CategoryChildDTO> categories);
    List<CategoryChildDTO> mapCategoriesEntityListToChildDto(List<CategoryEntity> categories);
    CategoryEntity mapCategoryChildDtoToEntity(CategoryChildDTO category);
    CategoryChildDTO mapCategoryEntityToChildDto(CategoryEntity category);

    //departmentDTO
    DepartmentEntity mapDepartmentDtoToEntity(DepartmentDTO dto);
    DepartmentDTO mapDepartmentEntityToDto(DepartmentEntity entity);

    //departmentChild
    DepartmentEntity mapDepartmentChildDtoToEntity(DepartmentChildDTO dto);
    DepartmentChildDTO mapDepartmentEntityToChildDto(DepartmentEntity entity);

    //departmentSelf
    DepartmentEntity mapDepartmentSelfDtoToEntity(DepartmentSelfDTO dto);
    DepartmentSelfDTO mapDepartmentEntityToSelfDto(DepartmentEntity entity);
}
