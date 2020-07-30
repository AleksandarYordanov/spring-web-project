package project.spring.project.eCommerce.cart.connectionEntity.cartProduct.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CartProductMapper {
    CartProductMapper INSTANCE = Mappers.getMapper(CartProductMapper.class);

    CartProductEntity mapTypeProductDtoToEntity(CartProductDTO dto);

    CartProductDTO mapTypeProductEntityToDto(CartProductEntity entity);
}
