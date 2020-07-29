package project.spring.project.admin.homePage.promoBanners.service;

import project.spring.project.admin.homePage.promoBanners.model.PromoBannerChildDTO;

import java.util.List;

public interface PromoBannerService {
    void createTopLeftDealWithImage(PromoBannerChildDTO promoBannerChildDTO, List<String> myParams);
    void updateTopLefDealWithImage(PromoBannerChildDTO promoBannerChildDTO, List<String> myParams);
    void deleteById(Long id);
    PromoBannerChildDTO getById(Long id);
    List<PromoBannerChildDTO> getAll();
    List<PromoBannerChildDTO> getAllActiveSorted(boolean active);
}
