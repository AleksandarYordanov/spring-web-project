package project.spring.project.eCommerce.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.spring.project.admin.type.model.TypeEntity;
import project.spring.project.eCommerce.cart.model.CartEntity;
import project.spring.project.user.model.UserEntity;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {
    Optional<CartEntity> getByUserId(Long userId);
    Optional<CartEntity> getBySession(String session);
}
