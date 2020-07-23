package project.spring.project.admin.product.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import project.spring.project.admin.utils.fileUpload.UploadFileDTO;
import project.spring.project.admin.utils.fileUpload.UploadFileEntity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDTO {

    private Long id;
    private String name;
    private Float price;
    private Integer stock;
    private boolean active;
    private boolean unlimited;
    private Timestamp addedDate;
    private String shortDescription;
    private String description;
    private List<UploadFileDTO> photos;

    public List<UploadFileDTO> getPhotos() {
        return photos;
    }

    public void setPhotos(List<UploadFileDTO> photos) {
        this.photos = photos;
    }

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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isUnlimited() {
        return unlimited;
    }

    public void setUnlimited(boolean unlimited) {
        this.unlimited = unlimited;
    }

    public Timestamp getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Timestamp addedDate) {
        this.addedDate = addedDate;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
