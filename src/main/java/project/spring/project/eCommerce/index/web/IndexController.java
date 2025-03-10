package project.spring.project.eCommerce.index.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import project.spring.project.admin.department.model.DepartmentDTO;
import project.spring.project.admin.department.service.DepartmentService;
import project.spring.project.admin.homePage.homeFeatured.model.HomeFeaturedChildDTO;
import project.spring.project.admin.homePage.homeFeatured.service.HomeFeaturedService;
import project.spring.project.admin.homePage.promoBanners.model.PromoBannerChildDTO;
import project.spring.project.admin.homePage.promoBanners.service.PromoBannerService;
import project.spring.project.admin.homePage.topLefDeals.model.TopLeftDealChildDTO;
import project.spring.project.admin.homePage.topLefDeals.service.TopLeftDealService;
import project.spring.project.admin.homePage.topSlider.model.TopSliderChildDTO;
import project.spring.project.admin.homePage.topSlider.service.TopSliderService;
import project.spring.project.eCommerce.cart.model.CartChildDTO;
import project.spring.project.eCommerce.cart.service.CartService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {
    private final DepartmentService departmentService;
    private final TopSliderService topSliderService;
    private final TopLeftDealService topLeftDealService;
    private final HomeFeaturedService homeFeaturedService;
    private final PromoBannerService promoBannerService;
    private final CartService cartService;

    public IndexController(DepartmentService departmentService, TopSliderService topSliderService, TopLeftDealService topLeftDealService, HomeFeaturedService homeFeaturedService, PromoBannerService promoBannerService, CartService cartService) {
        this.departmentService = departmentService;
        this.topSliderService = topSliderService;
        this.topLeftDealService = topLeftDealService;
        this.homeFeaturedService = homeFeaturedService;
        this.promoBannerService = promoBannerService;
        this.cartService = cartService;
    }

    @GetMapping()
    public ModelAndView dashboard(Principal principal) {
        ModelAndView modelAndView  = new ModelAndView("commerce/index/index");
        List<DepartmentDTO> departmentDTOList = departmentService.getAllWitchCategoryChildDTO();
        modelAndView.addObject("departmentDtoList",departmentDTOList);

        List<TopSliderChildDTO> topSliderChildDTOList = topSliderService.getAllActiveSorted(true);
        modelAndView.addObject("topSliderChildDTOList",topSliderChildDTOList);

        List<TopLeftDealChildDTO> topLeftDealChildDTOList = topLeftDealService.getAllActiveSorted(true);
        modelAndView.addObject("topLeftDealChildDTOList",topLeftDealChildDTOList);

        List<HomeFeaturedChildDTO>  homeFeaturedChildDTOList = homeFeaturedService.getAllActiveSortedWithoutMain(true);
        modelAndView.addObject("homeFeaturedChildDTOList",homeFeaturedChildDTOList);

        HomeFeaturedChildDTO homeFeaturedMainChildDto = homeFeaturedService.getMain();
        modelAndView.addObject("homeFeaturedMainChildDto",homeFeaturedMainChildDto);

        List<PromoBannerChildDTO> promoBannerChildDTOList = promoBannerService.getAllActiveSorted(true);
        modelAndView.addObject("promoBannerChildDTOList",promoBannerChildDTOList);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        CartChildDTO cartChildDTO = cartService.getByAuthentication(authentication);
        modelAndView.addObject(cartChildDTO);

        return modelAndView;
    }

}
