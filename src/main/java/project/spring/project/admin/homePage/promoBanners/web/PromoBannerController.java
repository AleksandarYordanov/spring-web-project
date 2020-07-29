package project.spring.project.admin.homePage.promoBanners.web;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import project.spring.project.admin.homePage.promoBanners.model.PromoBannerChildDTO;
import project.spring.project.admin.homePage.promoBanners.service.PromoBannerService;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin/promo-banner")
public class PromoBannerController {

    private final PromoBannerService promoBannerService;

    public PromoBannerController(PromoBannerService promoBannerService) {
        this.promoBannerService = promoBannerService;
    }

    @GetMapping("/create")
    public ModelAndView createGet(Model model) {

        ModelAndView modelAndView  = new ModelAndView("admin/home-page/promo-banner/promo-banner-create");
        return modelAndView;
    }

    @PostMapping("/create")
    public String createPost(@RequestParam("imageBits") List<String> myParams, @ModelAttribute PromoBannerChildDTO promoBannerChildDTO) {

        myParams.removeAll(Arrays.asList("",null));
        if (myParams.isEmpty()){
            return "redirect:/admin/home-page";
        }

        promoBannerService.createTopLeftDealWithImage(promoBannerChildDTO,myParams);
        return "redirect:/admin/home-page";
    }

    @GetMapping("details/{id}")
    public ModelAndView detailsGet(@PathVariable(value="id") Long id) {

        ModelAndView modelAndView  = new ModelAndView("admin/home-page/promo-banner/promo-banner-details");
        PromoBannerChildDTO promoBannerChildDTO = promoBannerService.getById(id);
        modelAndView.addObject(promoBannerChildDTO);
        return modelAndView;
    }

    @PostMapping("details/{id}")
    public String detailsPost(@RequestParam("imageBits") List<String> myParams, @ModelAttribute PromoBannerChildDTO promoBannerChildDTO, @PathVariable(value="id") Long id) {

        myParams.removeAll(Arrays.asList("",null));
        if (myParams.isEmpty()){
            return "redirect:/admin/home-page";
        }


        promoBannerService.updateTopLefDealWithImage(promoBannerChildDTO,myParams);
        return "redirect:/admin/home-page";
    }

    @GetMapping("delete/{id}")
    public String topSliderDelete(@PathVariable(value="id") Long id) {


        try {

            promoBannerService.deleteById(id);
            return "redirect:/admin/home-page/" ;
        } catch (Exception ex) {
            return "redirect:/admin/home-page/";
        }
    }


}
