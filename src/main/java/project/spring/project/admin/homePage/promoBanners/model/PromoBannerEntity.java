package project.spring.project.admin.homePage.promoBanners.model;


import lombok.Data;
import project.spring.project.admin.utils.fileUpload.UploadFileEntity;

import javax.persistence.*;

@Data
@Entity
@Table(name="home_promo_banners")
public class PromoBannerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "photo_id", referencedColumnName = "id")
    private UploadFileEntity photo;

    @Column
    @Lob
    private String description;

    @Column
    private String link;

    @Column
    private boolean active;

    @Column
    private Integer position;

    @Column
    private String background;

    @Column
    private String date;

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
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

    public UploadFileEntity getPhoto() {
        return photo;
    }

    public void setPhoto(UploadFileEntity photo) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
