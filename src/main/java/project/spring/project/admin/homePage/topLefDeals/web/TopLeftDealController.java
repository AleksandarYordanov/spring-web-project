package project.spring.project.admin.homePage.topLefDeals.web;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import project.spring.project.admin.homePage.topLefDeals.model.TopLeftDealChildDTO;
import project.spring.project.admin.homePage.topLefDeals.service.TopLeftDealService;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin/top-left-deal")
public class TopLeftDealController {

    private final TopLeftDealService topLeftDealService;

    public TopLeftDealController(TopLeftDealService topLeftDealService) {
        this.topLeftDealService = topLeftDealService;
    }

    @GetMapping("/create")
    public ModelAndView createGet(Model model) {

        ModelAndView modelAndView  = new ModelAndView("admin/home-page/top-left-deal/top-left-deal-create");
        return modelAndView;
    }

    @PostMapping("/create")
    public String createPost(@RequestParam("imageBits") List<String> myParams, @ModelAttribute TopLeftDealChildDTO topLeftDealChildDTO) {

        myParams.removeAll(Arrays.asList("",null));
        if (myParams.isEmpty()){
            return "redirect:/admin/home-page";
        }

        topLeftDealService.createTopLeftDealWithImage(topLeftDealChildDTO,myParams);
        return "redirect:/admin/home-page";
    }

    @GetMapping("details/{id}")
    public ModelAndView detailsGet(@PathVariable(value="id") Long id) {

        ModelAndView modelAndView  = new ModelAndView("admin/home-page/top-left-deal/top-left-deal-details");
        TopLeftDealChildDTO topLeftDealChildDTO = topLeftDealService.getById(id);
        modelAndView.addObject(topLeftDealChildDTO);
        return modelAndView;
    }

    @PostMapping("details/{id}")
    public String detailsPost(@RequestParam("imageBits") List<String> myParams, @ModelAttribute TopLeftDealChildDTO topLeftDealChildDTO, @PathVariable(value="id") Long id) {

        myParams.removeAll(Arrays.asList("",null));
        if (myParams.isEmpty()){
            return "redirect:/admin/home-page";
        }


        topLeftDealService.updateTopLefDealWithImage(topLeftDealChildDTO,myParams);
        return "redirect:/admin/home-page";
    }

    @GetMapping("delete/{id}")
    public String topSliderDelete(@PathVariable(value="id") Long id) {


        try {

            topLeftDealService.deleteById(id);
            return "redirect:/admin/home-page/" ;
        } catch (Exception ex) {
            return "redirect:/admin/home-page/";
        }
    }


}
