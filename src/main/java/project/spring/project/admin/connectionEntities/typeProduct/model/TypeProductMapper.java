package project.spring.project.admin.connectionEntities.typeProduct.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TypeProductMapper {
    TypeProductMapper INSTANCE = Mappers.getMapper(TypeProductMapper.class);

    TypeProductEntity mapTypeProductDtoToEntity(TypeProductDTO dto);

    TypeProductDTO mapTypeProductEntityToDto(TypeProductEntity entity);
}
