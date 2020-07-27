package project.spring.project.admin.homePage.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import project.spring.project.admin.homePage.topSlider.model.TopSliderChildDTO;
import project.spring.project.admin.homePage.topSlider.service.TopSliderService;

import java.util.List;

@Controller
@RequestMapping("/admin/home-page")
public class AdminHomePageController {
    private final TopSliderService topSliderService;

    public AdminHomePageController(TopSliderService topSliderService) {
        this.topSliderService = topSliderService;
    }


    @GetMapping()
    public ModelAndView home(Model model) {

        ModelAndView modelAndView  = new ModelAndView("admin/home-page/home-page-elements");
        List<TopSliderChildDTO> allTopSlider = topSliderService.getAll();
        modelAndView.addObject("allTopSlider",allTopSlider);
        return modelAndView;
    }
}
