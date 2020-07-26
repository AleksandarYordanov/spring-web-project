package project.spring.project.admin.type.model;

import project.spring.project.admin.category.model.CategorySelfDTO;
import project.spring.project.admin.product.model.ProductChildDTO;

import java.sql.Timestamp;
import java.util.List;

public class TypeSelfDTO {
    private Long id;
    private String name;
    private boolean active;
    private Timestamp addedDate;

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
}
