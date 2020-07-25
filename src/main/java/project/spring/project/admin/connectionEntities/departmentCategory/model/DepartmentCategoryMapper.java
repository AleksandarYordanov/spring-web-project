package project.spring.project.admin.connectionEntities.departmentCategory.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DepartmentCategoryMapper {
    DepartmentCategoryMapper INSTANCE = Mappers.getMapper(DepartmentCategoryMapper.class);

    DepartmentCategoryEntity mapDepartmentCategoryDtoToEntity(DepartmentCategoryDTO dto);

    DepartmentCategoryDTO mapDepartmentCategoryEntityToDto(DepartmentCategoryEntity entity);
}
