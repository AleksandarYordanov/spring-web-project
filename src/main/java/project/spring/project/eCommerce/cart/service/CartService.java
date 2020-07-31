package project.spring.project.eCommerce.cart.service;

import org.springframework.security.core.Authentication;
import project.spring.project.eCommerce.cart.model.CartChildDTO;
import project.spring.project.eCommerce.cart.model.CartEntity;

public interface CartService {
    CartChildDTO getByAuthentication(Authentication authentication);
     CartEntity getEntityByAuthentication(Authentication authentication);
    void addProductToCart(Authentication authentication,Long productId);
    void removeProductFromCart(Authentication authentication,Long productId);
    void emptyCartByAuthentication(Authentication authentication);
}
