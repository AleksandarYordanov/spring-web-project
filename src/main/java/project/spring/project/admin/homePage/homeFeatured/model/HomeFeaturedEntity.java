package project.spring.project.admin.homePage.homeFeatured.model;


import lombok.Data;
import project.spring.project.admin.product.model.ProductEntity;
import project.spring.project.admin.utils.fileUpload.UploadFileEntity;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="home_home_featured")
public class HomeFeaturedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private boolean active;

    @Column
    private Integer position;

    private boolean main;

    @ManyToMany
    @JoinTable( name = "home_featured_products",
            joinColumns = @JoinColumn(referencedColumnName = "id", name = "homeFeaturedId"),
            inverseJoinColumns = @JoinColumn(referencedColumnName = "id", name = "productId"))
    private List<ProductEntity> products;

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

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }

    public boolean isMain() {
        return main;
    }

    public void setMain(boolean main) {
        this.main = main;
    }
}
