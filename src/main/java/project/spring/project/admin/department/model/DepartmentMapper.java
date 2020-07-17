package project.spring.project.admin.department.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DepartmentMapper {

    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);

    DepartmentEntity mapDepartmentDtoToEntity(DepartmentDTO dto);

    DepartmentDTO mapDepartmentEntityToDto(DepartmentEntity entity);
}
