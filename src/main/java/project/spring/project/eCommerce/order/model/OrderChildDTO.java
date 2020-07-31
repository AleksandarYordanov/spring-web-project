package project.spring.project.eCommerce.order.model;

import project.spring.project.admin.product.model.ProductChildDTO;

import java.util.List;

public class OrderChildDTO {
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

    public double getTotalPrice(){
        if (getProducts() == null){
            return 0;
        }
        return getProducts().stream().mapToDouble(ProductChildDTO::getPrice).sum();
    }

    public int getProductsCount(){
        if (getProducts() == null){
            return 0;
        }
        return getProducts().size();
    }
}
