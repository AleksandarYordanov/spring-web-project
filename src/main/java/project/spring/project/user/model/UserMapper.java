package project.spring.project.user.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import project.spring.project.admin.product.model.ProductEntity;
import project.spring.project.eCommerce.cart.model.CartMapper;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Mapper
public interface UserMapper {


    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    RoleDTO mapRoleEntityToDTO (RoleEntity entity);

    UserDTO mapUserEntityToDTO(UserEntity entity);
    UserEntity mapUserDTOToEntity(UserDTO dto);
}
