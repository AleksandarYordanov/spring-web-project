package project.spring.project.eCommerce.cart.connectionEntity.cartProduct.service;

import project.spring.project.eCommerce.cart.connectionEntity.cartProduct.model.CartProductDTO;
import project.spring.project.eCommerce.cart.connectionEntity.cartProduct.model.CartProductPK;

import java.util.List;

public interface CartProductService {
    List<CartProductDTO> getAll();
    void create(CartProductDTO cartProductDTO);

    void deleteById(CartProductPK cartProductPK);

    CartProductDTO getById(CartProductPK id);

    void update(CartProductDTO cartProductDTO);
}
