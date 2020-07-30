package project.spring.project.eCommerce.cart.connectionEntity.cartProduct.service;

import org.springframework.stereotype.Service;
import project.spring.project.eCommerce.cart.connectionEntity.cartProduct.model.CartProductDTO;
import project.spring.project.eCommerce.cart.connectionEntity.cartProduct.model.CartProductEntity;
import project.spring.project.eCommerce.cart.connectionEntity.cartProduct.model.CartProductMapper;
import project.spring.project.eCommerce.cart.connectionEntity.cartProduct.model.CartProductPK;
import project.spring.project.eCommerce.cart.connectionEntity.cartProduct.repository.CartProductRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartProductServiceImpl implements CartProductService {
    private final CartProductRepo cartProductRepo;

    public CartProductServiceImpl(CartProductRepo cartProductRepo) {
        this.cartProductRepo = cartProductRepo;
    }


    @Override
    public List<CartProductDTO> getAll() {
        return cartProductRepo.
                findAll().
                stream().
                map(CartProductMapper.INSTANCE::mapTypeProductEntityToDto).
                collect(Collectors.toList());
    }

    @Override
    public void create(CartProductDTO cartProductDto) {
        CartProductEntity departmentCategoryEntity = CartProductMapper.INSTANCE.mapTypeProductDtoToEntity(cartProductDto);
        cartProductRepo.save(departmentCategoryEntity);
    }

    @Override
    public void deleteById(CartProductPK departmentCategoryPK) {
        cartProductRepo.deleteById(departmentCategoryPK);
    }


    @Override
    public CartProductDTO getById(CartProductPK id) {
        CartProductEntity departmentCategoryEntity = cartProductRepo.getOne(id);
        return CartProductMapper.INSTANCE.mapTypeProductEntityToDto(departmentCategoryEntity);


    }

    @Override
    public void update(CartProductDTO cartProductDto) {
        CartProductEntity departmentCategoryEntity = CartProductMapper.INSTANCE.mapTypeProductDtoToEntity(cartProductDto);
        cartProductRepo.save(departmentCategoryEntity);
    }
}
