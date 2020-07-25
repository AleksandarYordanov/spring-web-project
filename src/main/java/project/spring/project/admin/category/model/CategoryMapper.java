package project.spring.project.admin.category.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import project.spring.project.admin.department.model.DepartmentDTO;
import project.spring.project.admin.department.model.DepartmentEntity;

import java.util.Set;


@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Set<DepartmentEntity> mapDepartmentDtoToEntity(Set<DepartmentDTO> dto);

    Set<DepartmentDTO> mapDepartmentEntityToDto(Set<DepartmentEntity> entity);


    CategoryEntity mapCategoryDtoToEntity(CategoryDTO dto);

    CategoryDTO mapCategoryEntityToDto(CategoryEntity entity);
}
