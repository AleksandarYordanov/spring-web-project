package project.spring.project.admin.type.model;

import project.spring.project.admin.category.model.CategoryDTO;
import project.spring.project.admin.category.model.CategorySelfDTO;
import project.spring.project.admin.product.model.ProductChildDTO;
import project.spring.project.admin.product.model.ProductDTO;
import project.spring.project.admin.product.model.ProductSelfDTO;

import java.sql.Timestamp;
import java.util.List;

public class TypeChildDTO {
    private Long id;
    private String name;
    private boolean active;
    private Timestamp addedDate;
    private List<ProductChildDTO> products;
    private List<CategorySelfDTO> categories;

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

    public Timestamp getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Timestamp addedDate) {
        this.addedDate = addedDate;
    }

    public List<ProductChildDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductChildDTO> products) {
        this.products = products;
    }

    public List<CategorySelfDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategorySelfDTO> categories) {
        this.categories = categories;
    }
}
