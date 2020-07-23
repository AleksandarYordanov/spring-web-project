package project.spring.project.admin.connectionEntities.departmentCategory;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import project.spring.project.admin.product.model.ProductDTO;
import project.spring.project.admin.product.model.ProductEntity;
import project.spring.project.admin.product.model.ProductMapper;

@Mapper
public interface DepartmentCategoryMapper {
    DepartmentCategoryMapper INSTANCE = Mappers.getMapper(DepartmentCategoryMapper.class);

    DepartmentCategoryEntity mapDepartmentCategoryDtoToEntity(DepartmentCategoryDTO dto);

    DepartmentCategoryDTO mapDepartmentCategoryEntityToDto(DepartmentCategoryEntity entity);
}
