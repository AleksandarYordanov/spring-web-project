package project.spring.project.admin.category.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import project.spring.project.admin.department.model.DepartmentDTO;
import project.spring.project.admin.department.model.DepartmentEntity;
import project.spring.project.admin.product.model.ProductMapper;
import project.spring.project.admin.type.model.TypeDTO;
import project.spring.project.admin.type.model.TypeEntity;
import project.spring.project.admin.type.model.TypeMapper;

import java.util.List;


@Mapper
public interface CategoryMapper  {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    //department
   List<DepartmentEntity> mapDepartmentDtoListToEntity(List<DepartmentDTO> dto);

   List<DepartmentDTO> mapDepartmentEntityListToDto(List<DepartmentEntity> entity);

    DepartmentEntity mapDepartmentDtoToEntity(DepartmentDTO departmentDTO);

    DepartmentDTO mapDepartmentDtoToEntity(DepartmentEntity departmentEntity);

    //type
   List<TypeEntity> mapTypeDtoListToEntity(List<TypeDTO> dto);

   List<TypeDTO> mapTypeEntityListToDto(List<TypeEntity> entity);

    TypeEntity mapTypeDtoToEntity(TypeDTO typeDTO);

    TypeDTO mapTypeEntityToDto(TypeEntity typeEntity);

    //category
    CategoryEntity mapCategoryDtoToEntity(CategoryDTO dto);

    CategoryDTO mapCategoryEntityToDto(CategoryEntity entity);
}
