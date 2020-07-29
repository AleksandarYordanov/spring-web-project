package project.spring.project.admin.homePage.homeFeatured.connectionEntity.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity(name="HomeFeaturedProductEntity")
@Table(name="home_featured_products")
public class HomeFeaturedProductEntity implements Serializable {


    @EmbeddedId
    private HomeFeaturedProductPK id;

    @Column
    private int position;

    public HomeFeaturedProductPK getId() {
        return id;
    }

    public void setId(HomeFeaturedProductPK id) {
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
