package project.spring.project.admin.department.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import project.spring.project.admin.category.model.CategoryDTO;
import project.spring.project.admin.category.model.CategoryEntity;
import project.spring.project.admin.connectionEntities.departmentCategory.DepartmentCategoryDTO;
import project.spring.project.admin.connectionEntities.departmentCategory.DepartmentCategoryEntity;
import project.spring.project.admin.product.model.ProductDTO;
import project.spring.project.admin.product.model.ProductEntity;

@Mapper
public interface DepartmentMapper {

    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);

    CategoryEntity mapCategoryDtoToEntity(CategoryDTO dto);

    CategoryDTO mapCategoryEntityToDto(CategoryEntity entity);

    DepartmentEntity mapDepartmentDtoToEntity(DepartmentDTO dto);

    DepartmentDTO mapDepartmentEntityToDto(DepartmentEntity entity);
}
