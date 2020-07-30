package project.spring.project.eCommerce.cart.model;

import project.spring.project.admin.product.model.ProductEntity;
import project.spring.project.admin.utils.fileUpload.UploadFileEntity;
import project.spring.project.user.model.RoleEntity;
import project.spring.project.user.model.UserEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="carts")
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable=false, updatable=false)
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity userId;

    @NotNull
    @Column(name="session", nullable = false, unique = true)
    private String session;

    @ManyToMany
    @JoinTable( name = "carts_products",
            joinColumns = @JoinColumn(referencedColumnName = "id", name = "cartId"),
            inverseJoinColumns = @JoinColumn(referencedColumnName = "id", name = "productId"))
    private List<ProductEntity> products;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserEntity getUserId() {
        return userId;
    }

    public void setUserId(UserEntity userId) {
        this.userId = userId;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }
}
