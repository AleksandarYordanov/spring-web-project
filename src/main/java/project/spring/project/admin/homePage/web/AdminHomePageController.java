package project.spring.project.admin.homePage.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import project.spring.project.admin.homePage.homeFeatured.model.HomeFeaturedChildDTO;
import project.spring.project.admin.homePage.homeFeatured.service.HomeFeaturedService;
import project.spring.project.admin.homePage.promoBanners.model.PromoBannerChildDTO;
import project.spring.project.admin.homePage.promoBanners.service.PromoBannerService;
import project.spring.project.admin.homePage.topLefDeals.model.TopLeftDealChildDTO;
import project.spring.project.admin.homePage.topLefDeals.service.TopLeftDealService;
import project.spring.project.admin.homePage.topSlider.model.TopSliderChildDTO;
import project.spring.project.admin.homePage.topSlider.service.TopSliderService;

import java.util.List;

@Controller
@RequestMapping("/admin/home-page")
public class AdminHomePageController {
    private final TopSliderService topSliderService;
    private final TopLeftDealService topLeftDealService;
    private final PromoBannerService promoBannerService;
    private final HomeFeaturedService homeFeaturedService;

    public AdminHomePageController(TopSliderService topSliderService, TopLeftDealService topLeftDealService, PromoBannerService promoBannerService, HomeFeaturedService homeFeaturedService) {
        this.topSliderService = topSliderService;
        this.topLeftDealService = topLeftDealService;
        this.promoBannerService = promoBannerService;
        this.homeFeaturedService = homeFeaturedService;
    }


    @GetMapping()
    public ModelAndView home(Model model) {

        ModelAndView modelAndView  = new ModelAndView("admin/home-page/home-page-elements");
        List<TopSliderChildDTO> allTopSlider = topSliderService.getAll();
        modelAndView.addObject("allTopSlider",allTopSlider);
        List<TopLeftDealChildDTO> allTopLeftDeal = topLeftDealService.getAll();
        modelAndView.addObject("allTopLeftDeal",allTopLeftDeal);
        List<PromoBannerChildDTO> allPromoBanner = promoBannerService.getAll();
        modelAndView.addObject("allPromoBanner",allPromoBanner);
        List<HomeFeaturedChildDTO> allHomeFeatured = homeFeaturedService.getAll();
        modelAndView.addObject("allHomeFeatured",allHomeFeatured);
        return modelAndView;
    }
}
