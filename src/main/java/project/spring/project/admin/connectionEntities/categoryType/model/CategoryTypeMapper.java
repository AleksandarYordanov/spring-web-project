package project.spring.project.admin.connectionEntities.categoryType.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryTypeMapper {
    CategoryTypeMapper INSTANCE = Mappers.getMapper(CategoryTypeMapper.class);

    CategoryTypeEntity mapDepartmentCategoryDtoToEntity(CategoryTypeDTO dto);

    CategoryTypeDTO mapDepartmentCategoryEntityToDto(CategoryTypeEntity entity);
}
