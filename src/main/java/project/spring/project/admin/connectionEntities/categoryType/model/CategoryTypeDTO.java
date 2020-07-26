package project.spring.project.admin.connectionEntities.categoryType.model;

public class CategoryTypeDTO {

    private CategoryTypePK id;
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
