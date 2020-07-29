package project.spring.project.admin.homePage.homeFeatured.model;

import project.spring.project.admin.product.model.ProductChildDTO;
import project.spring.project.admin.utils.fileUpload.UploadFileSelfDTO;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

public class HomeFeaturedChildDTO {
    private Long id;
    private String name;
    private boolean active;
    private Integer position;
    private boolean main;
    private List<ProductChildDTO> products;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public List<ProductChildDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductChildDTO> products) {
        this.products = products;
    }

    public boolean isMain() {
        return main;
    }

    public void setMain(boolean main) {
        this.main = main;
    }
}
