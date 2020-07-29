package project.spring.project.admin.homePage.promoBanners.model;

import project.spring.project.admin.utils.fileUpload.UploadFileSelfDTO;

public class PromoBannerChildDTO {
    private Long id;
    private String name;
    private UploadFileSelfDTO photo;
    private String description;
    private String link;
    private boolean active;
    private Integer position;
    private String background;
    private String date;

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UploadFileSelfDTO getPhoto() {
        return photo;
    }

    public void setPhoto(UploadFileSelfDTO photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
