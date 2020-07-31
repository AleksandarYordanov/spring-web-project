package project.spring.project.eCommerce.order.connectionEntity.orderProduct.model;

public class OrderProductDTO {

    private OrderProductPK id;
    private int quantity;

    public OrderProductPK getId() {
        return id;
    }

    public void setId(OrderProductPK id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
