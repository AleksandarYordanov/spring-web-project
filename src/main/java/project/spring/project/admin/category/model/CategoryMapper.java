package project.spring.project.admin.category.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import project.spring.project.admin.connectionEntities.departmentCategory.DepartmentCategoryDTO;
import project.spring.project.admin.connectionEntities.departmentCategory.DepartmentCategoryEntity;
import project.spring.project.admin.department.model.DepartmentDTO;
import project.spring.project.admin.department.model.DepartmentEntity;


@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    DepartmentEntity mapDepartmentDtoToEntity(DepartmentDTO dto);

    DepartmentDTO mapDepartmentEntityToDto(DepartmentEntity entity);


    CategoryEntity mapCategoryDtoToEntity(CategoryDTO dto);

    CategoryDTO mapCategoryEntityToDto(CategoryEntity entity);
}
