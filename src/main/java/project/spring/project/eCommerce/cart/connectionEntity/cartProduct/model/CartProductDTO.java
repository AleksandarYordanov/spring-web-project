package project.spring.project.eCommerce.cart.connectionEntity.cartProduct.model;

public class CartProductDTO {

    private CartProductPK id;
    private int position;

    public CartProductPK getId() {
        return id;
    }

    public void setId(CartProductPK id) {
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
