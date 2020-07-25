package project.spring.project.admin.type.model;

import lombok.Data;
import project.spring.project.admin.category.model.CategoryEntity;
import project.spring.project.admin.product.model.ProductEntity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@Table(name="types")
public class TypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private boolean active;

    @Column
    private Timestamp addedDate;


    @ManyToMany
    @JoinTable( name = "types_products",
            joinColumns = @JoinColumn(referencedColumnName = "id", name = "typeId"),
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

    public Timestamp getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Timestamp addedDate) {
        this.addedDate = addedDate;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }
}
