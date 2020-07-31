package project.spring.project.eCommerce.cart.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import project.spring.project.eCommerce.cart.service.CartService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CartController {
        private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }


    @PostMapping("cart/add/{productId}")
    public String cartAdd( @PathVariable(value="productId") Long productId,   HttpServletRequest request) {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
           cartService.addProductToCart(authentication,productId);

        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;

    }

    @PostMapping("cart/remove/{productId}")
    public String cartRemove( @PathVariable(value="productId") Long productId,    HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        cartService.removeProductFromCart(authentication,productId);

        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }

}
