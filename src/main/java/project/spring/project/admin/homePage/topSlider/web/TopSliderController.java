package project.spring.project.admin.homePage.topSlider.web;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import project.spring.project.admin.homePage.topSlider.model.TopSliderChildDTO;
import project.spring.project.admin.homePage.topSlider.service.TopSliderService;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin/top-slider")
public class TopSliderController {

    private final TopSliderService topSliderService;

    public TopSliderController(TopSliderService topSliderService) {
        this.topSliderService = topSliderService;
    }

    @GetMapping("/create")
    public ModelAndView createGet(Model model) {

        ModelAndView modelAndView  = new ModelAndView("admin/home-page/top-slider/top-slider-create");
        return modelAndView;
    }

    @PostMapping("/create")
    public String createPost(@RequestParam("imageBits") List<String> myParams, @ModelAttribute TopSliderChildDTO topSliderChildDTO) {

        myParams.removeAll(Arrays.asList("",null));
        if (myParams.isEmpty()){
            return "redirect:/admin/home-page";
        }

        topSliderService.createTopSliderWithImage(topSliderChildDTO,myParams);
        return "redirect:/admin/home-page";
    }

    @GetMapping("details/{id}")
    public ModelAndView detailsGet(@PathVariable(value="id") Long id) {

        ModelAndView modelAndView  = new ModelAndView("admin/home-page/top-slider/top-slider-details");
        TopSliderChildDTO topSliderChildDTO = topSliderService.getById(id);
        modelAndView.addObject(topSliderChildDTO);
        return modelAndView;
    }

    @PostMapping("details/{id}")
    public String detailsPost(@RequestParam("imageBits") List<String> myParams, @ModelAttribute TopSliderChildDTO topSliderChildDTO,@PathVariable(value="id") Long id) {

        myParams.removeAll(Arrays.asList("",null));
        if (myParams.isEmpty()){
            return "redirect:/admin/home-page";
        }


        topSliderService.updateTopSliderWithImage(topSliderChildDTO,myParams);
        return "redirect:/admin/home-page";
    }

    @GetMapping("delete/{id}")
    public String topSliderDelete(@PathVariable(value="id") Long id) {


        try {

            topSliderService.deleteById(id);
            return "redirect:/admin/home-page/" ;
        } catch (Exception ex) {
            return "redirect:/admin/home-page/";
        }
    }


}
