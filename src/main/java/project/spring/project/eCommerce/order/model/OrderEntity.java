package project.spring.project.eCommerce.order.model;

import project.spring.project.admin.product.model.ProductEntity;
import project.spring.project.user.model.UserEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable=false, updatable=false)
    private long id;

    // TODO:FIX COLUMS AND ETC
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;


    @Column(name="session",  unique = true)
    private String session;

    @ManyToMany
    @JoinTable( name = "orders_products",
            joinColumns = @JoinColumn(referencedColumnName = "id", name = "orderId"),
            inverseJoinColumns = @JoinColumn(referencedColumnName = "id", name = "productId"))
    private List<ProductEntity> products;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
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
