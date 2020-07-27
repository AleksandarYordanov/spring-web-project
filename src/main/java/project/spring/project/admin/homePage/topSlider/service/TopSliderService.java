package project.spring.project.admin.homePage.topSlider.service;

import project.spring.project.admin.homePage.topSlider.model.TopSliderChildDTO;
import project.spring.project.admin.product.model.ProductChildDTO;
import project.spring.project.admin.product.model.ProductDTO;

import java.util.List;

public interface TopSliderService {
    void createTopSliderWithImage(TopSliderChildDTO topSliderChildDTO, List<String> myParams);
    void updateTopSliderWithImage(TopSliderChildDTO topSliderChildDTO, List<String> myParams);
    void deleteById(Long id);
    TopSliderChildDTO getById(Long id);
    List<TopSliderChildDTO> getAll();
    List<TopSliderChildDTO> getAllActiveSorted(boolean active);
}
