package project.spring.project.eCommerce.cart.service;

import org.hibernate.boot.jaxb.hbm.internal.CacheAccessTypeConverter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import project.spring.project.eCommerce.cart.model.CartChildDTO;
import project.spring.project.eCommerce.cart.model.CartEntity;
import project.spring.project.eCommerce.cart.model.CartMapper;
import project.spring.project.eCommerce.cart.repository.CartRepository;
import project.spring.project.user.model.UserEntity;
import project.spring.project.user.security.UserService;

import java.util.Optional;

public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final UserService userService;

    public CartServiceImpl(CartRepository cartRepository, UserService userService) {
        this.cartRepository = cartRepository;

        this.userService = userService;
    }

    @Override
    public CartChildDTO getByAuthentication(Authentication authentication) {
        CartEntity cartEntity;
        String session =   RequestContextHolder.currentRequestAttributes().getSessionId();
       if (authentication.isAuthenticated()){
           String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
          cartEntity = getCartOrCreateByUserAndSession(userService.getIdOfUser(currentUserName),session,currentUserName);
       }else {
           cartEntity = getCartOrCreateBySession(session);
       }
       return CartMapper.INSTANCE.mapCartEntityToCartChildDTO(cartEntity);
    }

    private CartEntity getCartOrCreateBySession(String session) {
        Optional<CartEntity> cartEntityOptional =
                cartRepository.getBySession(session);

        return cartEntityOptional.orElseGet(()->createBySession(session));
    }

    private CartEntity createBySession(String session) {
        CartEntity cartEntity  = new CartEntity();
        cartEntity.setSession(session);
        return cartRepository.save(cartEntity);
    }

    private CartEntity getCartOrCreateByUserAndSession(Long idOfUser, String session, String currentUserName) {
        Optional<CartEntity> cartEntityOptional =
                cartRepository.getByUserId(idOfUser);
        
        return cartEntityOptional.orElseGet(()->createByUIDAndSession(idOfUser,session,currentUserName));
    }

    private CartEntity createByUIDAndSession(Long idOfUser, String session, String currentUserName) {
        CartEntity cartEntity = cartRepository.getBySession(session).orElseGet(CartEntity::new);
        cartEntity.setUserId(userService.getOrCreateUserEntity(currentUserName));
        return cartRepository.save(cartEntity);

    }
}
