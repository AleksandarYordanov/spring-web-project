package project.spring.project.admin.utils.fileUpload;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import project.spring.project.admin.product.model.ProductEntity;

import javax.persistence.*;

@Entity
@Table(name = "uploaded_files")
public class UploadFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String location;

    @Column
    private Long size;

    @Column
    private int position;

    @Column
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ProductEntity product;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }
}
