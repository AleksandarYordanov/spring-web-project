package project.spring.project.admin.homePage.topLefDeals.service;

import project.spring.project.admin.homePage.topLefDeals.model.TopLeftDealChildDTO;

import java.util.List;

public interface TopLeftDealService {
    void createTopLeftDealWithImage(TopLeftDealChildDTO topLeftDealChildDTO, List<String> myParams);
    void updateTopLefDealWithImage(TopLeftDealChildDTO topLeftDealChildDTO, List<String> myParams);
    void deleteById(Long id);
    TopLeftDealChildDTO getById(Long id);
    List<TopLeftDealChildDTO> getAll();
    List<TopLeftDealChildDTO> getAllActiveSorted(boolean active);
}
