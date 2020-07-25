package project.spring.project.admin.connectionEntities.typeProduct.model;

public class TypeProductDTO {

    private TypeProductPK id;
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
