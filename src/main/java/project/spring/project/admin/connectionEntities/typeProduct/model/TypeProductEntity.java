package project.spring.project.admin.connectionEntities.typeProduct.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity(name="TypeProductEntity")
@Table(name="types_products")
public class TypeProductEntity implements Serializable {


    @EmbeddedId
    private TypeProductPK id;

    @Column
    private int position;

    public TypeProductPK getId() {
        return id;
    }

    public void setId(TypeProductPK id) {
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
