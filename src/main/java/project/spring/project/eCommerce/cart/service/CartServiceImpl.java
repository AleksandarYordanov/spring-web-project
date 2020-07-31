package project.spring.project.eCommerce.cart.service;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import project.spring.project.admin.product.model.ProductChildDTO;
import project.spring.project.admin.product.model.ProductEntity;
import project.spring.project.admin.product.repository.ProductRepository;
import project.spring.project.admin.utils.fileUpload.UploadFileService;
import project.spring.project.eCommerce.cart.model.CartChildDTO;
import project.spring.project.eCommerce.cart.model.CartEntity;
import project.spring.project.eCommerce.cart.model.CartMapper;
import project.spring.project.eCommerce.cart.repository.CartRepository;
import project.spring.project.user.model.UserEntity;
import project.spring.project.user.security.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final UserService userService;
    private final ProductRepository productRepository;
    private final UploadFileService uploadFileService;

    public CartServiceImpl(CartRepository cartRepository, UserService userService, ProductRepository productRepository, UploadFileService uploadFileService) {
        this.cartRepository = cartRepository;

        this.userService = userService;
        this.productRepository = productRepository;
        this.uploadFileService = uploadFileService;
    }

    @Override
    public CartChildDTO getByAuthentication(Authentication authentication) {
        CartEntity cartEntity = getEntityByAuthentication(authentication);

       CartChildDTO cartChildDTO = CartMapper.INSTANCE.mapCartEntityToCartChildDTO(cartEntity);
       List<ProductChildDTO> productChildDTOS = cartChildDTO.getProducts();
       if (productChildDTOS != null) {
           productChildDTOS.forEach(p -> {
               p.setPhotos(uploadFileService.getAllPhotosForProduct(p.getId()));
           });
           cartChildDTO.setProducts(productChildDTOS);
       }
        return cartChildDTO;
    }

    public CartEntity getEntityByAuthentication(Authentication authentication) {
        CartEntity cartEntity;
        String session =   RequestContextHolder.currentRequestAttributes().getSessionId();
        if (authentication instanceof AnonymousAuthenticationToken){
            cartEntity = getCartOrCreateBySession(session);

        }else {
            String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
            cartEntity = getCartOrCreateByUserAndSession(userService.getIdOfUser(currentUserName),session,currentUserName);
        }


        return cartEntity;
    }

    @Override
    public void addProductToCart(Authentication authentication, Long productId) {
      CartEntity cartEntity =  getEntityByAuthentication(authentication);
        ProductEntity productEntity = productRepository.getOne(productId);
        List<ProductEntity> currentProducts = cartEntity.getProducts();
        currentProducts.add(productEntity);
        cartEntity.setProducts(currentProducts);
        cartRepository.save(cartEntity);
    }

    @Override
    public void removeProductFromCart(Authentication authentication, Long productId) {
        CartEntity cartEntity =  getEntityByAuthentication(authentication);
        ProductEntity productEntity = productRepository.getOne(productId);
        List<ProductEntity> currentProducts = cartEntity.getProducts();
        currentProducts.remove(productEntity);
        cartEntity.setProducts(currentProducts);
        cartRepository.save(cartEntity);
    }

    @Override
    public void emptyCartByAuthentication(Authentication authentication) {
        CartEntity cartEntity = getEntityByAuthentication(authentication);
        cartEntity.setProducts(new ArrayList<>());
        cartRepository.save(cartEntity);
    }

    private CartEntity getCartOrCreateBySession(String session) {
        Optional<CartEntity> cartEntityOptional =
                cartRepository.findOneBySession(session);

        return cartEntityOptional.orElseGet(()->createBySession(session));
    }

    private CartEntity createBySession(String session) {
        CartEntity cartEntity  = new CartEntity();
        cartEntity.setSession(session);
        return cartRepository.save(cartEntity);
    }

    private CartEntity getCartOrCreateByUserAndSession(Long idOfUser, String session, String currentUserName) {

        UserEntity userEntity = userService.getById(idOfUser);
        Optional<CartEntity> cartEntityOptional =
                cartRepository.findOneByUserId( userEntity);

        System.out.println(cartEntityOptional);
        return cartEntityOptional.orElseGet(()->createByUIDAndSession(userEntity,session,currentUserName));
    }

    private CartEntity createByUIDAndSession(UserEntity userEntity, String session, String currentUserName) {
        CartEntity cartEntity = cartRepository.findOneBySession(session).orElseGet(CartEntity::new);
        cartEntity.setUserId(userEntity);
        cartEntity.setSession(session);
        return cartRepository.save(cartEntity);

    }
}
