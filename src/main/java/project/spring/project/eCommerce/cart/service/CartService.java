package project.spring.project.eCommerce.cart.service;

import org.springframework.security.core.Authentication;
import project.spring.project.eCommerce.cart.model.CartChildDTO;

public interface CartService {
    CartChildDTO getByAuthentication(Authentication authentication);
}
