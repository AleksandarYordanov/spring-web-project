package project.spring.project.eCommerce.cart.connectionEntity.cartProduct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.spring.project.eCommerce.cart.connectionEntity.cartProduct.model.CartProductEntity;
import project.spring.project.eCommerce.cart.connectionEntity.cartProduct.model.CartProductPK;

@Repository
public interface CartProductRepo extends JpaRepository<CartProductEntity, CartProductPK> {

}
