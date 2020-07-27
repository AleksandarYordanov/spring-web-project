package project.spring.project.admin.homePage.topSlider.model;

import project.spring.project.admin.utils.fileUpload.UploadFileEntity;
import project.spring.project.admin.utils.fileUpload.UploadFileSelfDTO;

import javax.persistence.*;

public class TopSliderChildDTO {
    private Long id;
    private String name;
    private UploadFileSelfDTO photo;
    private String description;
    private String link;
    private boolean active;
    private Integer position;

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
}
