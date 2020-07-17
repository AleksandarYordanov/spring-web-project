package project.spring.project.admin.category.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryEntity mapDepartmentDtoToEntity(CategoryDTO dto);

    CategoryDTO mapCategoryEntityToDto(CategoryEntity entity);
}
