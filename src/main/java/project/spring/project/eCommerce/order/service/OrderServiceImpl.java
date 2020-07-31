package project.spring.project.eCommerce.order.service;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import project.spring.project.admin.product.model.ProductChildDTO;
import project.spring.project.admin.product.model.ProductEntity;
import project.spring.project.admin.product.repository.ProductRepository;
import project.spring.project.admin.utils.fileUpload.UploadFileService;
import project.spring.project.eCommerce.cart.service.CartService;
import project.spring.project.eCommerce.order.model.OrderChildDTO;
import project.spring.project.eCommerce.order.model.OrderEntity;
import project.spring.project.eCommerce.order.model.OrderMapper;
import project.spring.project.eCommerce.order.repository.OrderRepository;
import project.spring.project.user.model.UserEntity;
import project.spring.project.user.security.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ProductRepository productRepository;
    private final UploadFileService uploadFileService;
    private final CartService cartService;

    public OrderServiceImpl(OrderRepository orderRepository, UserService userService, ProductRepository productRepository, UploadFileService uploadFileService, CartService cartService) {
        this.orderRepository = orderRepository;

        this.userService = userService;
        this.productRepository = productRepository;
        this.uploadFileService = uploadFileService;
        this.cartService = cartService;
    }

    @Override
    public OrderChildDTO getByAuthentication(Authentication authentication) {
        OrderEntity orderEntity = getEntityByAuthentication(authentication);

       OrderChildDTO orderChildDTO = OrderMapper.INSTANCE.mapCartEntityToCartChildDTO(orderEntity);
       List<ProductChildDTO> productChildDTOS = orderChildDTO.getProducts();
       if (productChildDTOS != null) {
           productChildDTOS.forEach(p -> {
               p.setPhotos(uploadFileService.getAllPhotosForProduct(p.getId()));
           });
           orderChildDTO.setProducts(productChildDTOS);
       }
        return orderChildDTO;
    }

    @Override
    public void createOrder(Authentication authentication , String email) {
        OrderEntity orderEntity = new OrderEntity();
        UserEntity userEntity = new UserEntity();


        if (email == null) {
         userEntity =   userService.getOrCreateUserEntity(authentication.getName());
        }else {
            userEntity = userService.getOrCreateUserEntity(email);
        }
        orderEntity.setUser(userEntity);
        List<ProductEntity> productsOfCart = cartService.getEntityByAuthentication(authentication).getProducts();
        List<ProductEntity> productsOfOrder = new ArrayList<>();
        productsOfCart.forEach(p->{
            productsOfOrder.add(productRepository.getOne(p.getId()));
        });

        orderEntity.setProducts(productsOfOrder);

        orderRepository.save(orderEntity);

        cartService.emptyCartByAuthentication(authentication);
    }

    private OrderEntity getEntityByAuthentication(Authentication authentication) {
        OrderEntity orderEntity;
        String session =   RequestContextHolder.currentRequestAttributes().getSessionId();
        if (authentication instanceof AnonymousAuthenticationToken){
            orderEntity = getCartOrCreateBySession(session);

        }else {
            String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
            orderEntity = getCartOrCreateByUserAndSession(userService.getIdOfUser(currentUserName),session,currentUserName);
        }


        return orderEntity;


    }



    @Override
    public void removeProductFromCart(Authentication authentication, Long productId) {
        OrderEntity orderEntity =  getEntityByAuthentication(authentication);
        ProductEntity productEntity = productRepository.getOne(productId);
        List<ProductEntity> currentProducts = orderEntity.getProducts();
        currentProducts.remove(productEntity);
        orderEntity.setProducts(currentProducts);
        orderRepository.save(orderEntity);
    }

    private OrderEntity getCartOrCreateBySession(String session) {
        Optional<OrderEntity> cartEntityOptional =
                orderRepository.findOneBySession(session);

        return cartEntityOptional.orElseGet(()->createBySession(session));
    }

    private OrderEntity createBySession(String session) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setSession(session);
        return orderRepository.save(orderEntity);
    }

    private OrderEntity getCartOrCreateByUserAndSession(Long idOfUser, String session, String currentUserName) {

        UserEntity userEntity = userService.getById(idOfUser);
        Optional<OrderEntity> cartEntityOptional =
                orderRepository.findOneByUserId( userEntity);

        System.out.println(cartEntityOptional);
        return cartEntityOptional.orElseGet(()->createByUIDAndSession(userEntity,session,currentUserName));
    }

    private OrderEntity createByUIDAndSession(UserEntity userEntity, String session, String currentUserName) {
        OrderEntity orderEntity = orderRepository.findOneBySession(session).orElseGet(OrderEntity::new);
        orderEntity.setUser(userEntity);
        orderEntity.setSession(session);
        return orderRepository.save(orderEntity);

    }
}
