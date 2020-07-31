package project.spring.project.eCommerce.order.connectionEntity.orderProduct.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderProductMapper {
    OrderProductMapper INSTANCE = Mappers.getMapper(OrderProductMapper.class);

    OrderProductEntity mapTypeProductDtoToEntity(OrderProductDTO dto);

    OrderProductDTO mapTypeProductEntityToDto(OrderProductEntity entity);
}
