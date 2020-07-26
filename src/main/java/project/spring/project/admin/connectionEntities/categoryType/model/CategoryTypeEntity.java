package project.spring.project.admin.connectionEntities.categoryType.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity(name="CategoryTypeEntity")
@Table(name="categories_types")
public class CategoryTypeEntity implements Serializable {


    @EmbeddedId
    private CategoryTypePK id;

    @Column
    private int position;

    public CategoryTypePK getId() {
        return id;
    }

    public void setId(CategoryTypePK id) {
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
