package project.spring.project.eCommerce.cart.model;

import project.spring.project.admin.category.model.CategorySelfDTO;
import project.spring.project.admin.product.model.ProductChildDTO;

import java.sql.Timestamp;
import java.util.List;

public class CartChildDTO {
    private Long id;
    private String session;
    private List<ProductChildDTO> products;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public List<ProductChildDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductChildDTO> products) {
        this.products = products;
    }


}
