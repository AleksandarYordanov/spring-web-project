package project.spring.project.eCommerce.order.service;

import org.springframework.security.core.Authentication;
import project.spring.project.eCommerce.order.model.OrderChildDTO;

public interface OrderService {
    OrderChildDTO getByAuthentication(Authentication authentication);
    void createOrder(Authentication authentication,String email);
    void removeProductFromCart(Authentication authentication, Long productId);
}
