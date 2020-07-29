package project.spring.project.admin.homePage.homeFeatured.connectionEntity.model;

public class HomeFeaturedProductDTO {

    private HomeFeaturedProductPK id;
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
