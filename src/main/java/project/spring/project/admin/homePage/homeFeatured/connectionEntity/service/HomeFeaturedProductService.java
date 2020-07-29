package project.spring.project.admin.homePage.homeFeatured.connectionEntity.service;

import project.spring.project.admin.homePage.homeFeatured.connectionEntity.model.HomeFeaturedProductDTO;
import project.spring.project.admin.homePage.homeFeatured.connectionEntity.model.HomeFeaturedProductPK;

import java.util.List;

public interface HomeFeaturedProductService {
    List<HomeFeaturedProductDTO> getAll();
    void create(HomeFeaturedProductDTO homeFeaturedProductDTO);

    void deleteById(HomeFeaturedProductPK homeFeaturedProductPK);

    HomeFeaturedProductDTO getById(HomeFeaturedProductPK id);

    void update(HomeFeaturedProductDTO homeFeaturedProductDTO);
}
