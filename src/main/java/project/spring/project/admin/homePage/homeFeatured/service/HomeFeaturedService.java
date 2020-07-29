package project.spring.project.admin.homePage.homeFeatured.service;

import project.spring.project.admin.homePage.homeFeatured.model.HomeFeaturedChildDTO;
import project.spring.project.admin.type.model.TypeChildDTO;
import project.spring.project.admin.type.model.TypeSelfDTO;

import java.util.List;

public interface HomeFeaturedService {

    void deleteById(Long id);
    HomeFeaturedChildDTO getById(Long id);
    List<HomeFeaturedChildDTO> getAll();
    List<HomeFeaturedChildDTO> getAllActiveSortedWithoutMain(boolean active);
    HomeFeaturedChildDTO getMain();
    void create(HomeFeaturedChildDTO type);
    void create(HomeFeaturedChildDTO type, List<Long> productIds);
    void update(HomeFeaturedChildDTO typeSelfDTO, List<Long> productIds);
}
