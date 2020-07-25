package project.spring.project.admin.department.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import project.spring.project.admin.category.model.CategoryDTO;
import project.spring.project.admin.category.model.CategoryEntity;

import java.util.List;

@Mapper
public interface DepartmentMapper {

    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);

    List<CategoryEntity> mapCategoriesDtoListToEntity(List<CategoryDTO> categories);

    List<CategoryDTO> mapCategoriesEntityListToDto(List<CategoryEntity> categories);

    CategoryEntity mapCategoryDtoToEntity(CategoryDTO category);

    CategoryDTO mapCategoryEntityToDto(CategoryEntity category);

    DepartmentEntity mapDepartmentDtoToEntity(DepartmentDTO dto);

    DepartmentDTO mapDepartmentEntityToDto(DepartmentEntity entity);
}
