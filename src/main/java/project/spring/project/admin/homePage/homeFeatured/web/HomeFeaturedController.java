package project.spring.project.admin.homePage.homeFeatured.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import project.spring.project.admin.homePage.homeFeatured.model.HomeFeaturedChildDTO;
import project.spring.project.admin.homePage.homeFeatured.service.HomeFeaturedService;
import project.spring.project.admin.product.model.ProductChildDTO;
import project.spring.project.admin.product.service.ProductService;

import java.util.List;

@Controller
@RequestMapping("/admin/home-featured")
public class HomeFeaturedController {

    private final HomeFeaturedService homeFeaturedService;
    private final ProductService productService;

    public HomeFeaturedController(HomeFeaturedService homeFeaturedService, ProductService productService) {
        this.homeFeaturedService = homeFeaturedService;
        this.productService = productService;
    }

    @GetMapping("details/{id}")
    public ModelAndView typeDetails(@PathVariable(value="id") Long id) {
        ModelAndView modelAndView = new ModelAndView("admin/home-page/home-featured/home-featured-details");
        HomeFeaturedChildDTO type = homeFeaturedService.getById(id);
        modelAndView.addObject(type);
        List<ProductChildDTO> allProducts = productService.getAll();
        modelAndView.addObject("allProducts",allProducts);
        modelAndView.addObject(id);
        return modelAndView;
    }

    @PostMapping("details/{id}")
    public String departmentDetailsSave(@ModelAttribute HomeFeaturedChildDTO homeFeaturedChildDTO, @RequestParam("productId") List<Long> productId
            , @PathVariable(value="id") Long id) {
        homeFeaturedChildDTO.setId(id);

        try {

            homeFeaturedService.update(homeFeaturedChildDTO,productId);
            return "redirect:/admin/home-page/" ;
        } catch (Exception ex) {
            return "redirect:/admin/home-page/";
        }
    }


    @GetMapping("/create")
    public ModelAndView departmentAdd() {
        ModelAndView modelAndView  = new ModelAndView("admin/home-page/home-featured/home-featured-create");
        List<ProductChildDTO> allProducts = productService.getAll();
        modelAndView.addObject("allProducts",allProducts);
        return modelAndView;
    }

    @PostMapping("/create")
    public String createDepartment(@ModelAttribute HomeFeaturedChildDTO homeFeaturedChildDTO, @RequestParam("productId") List<Long> productId) {

        try {

            homeFeaturedService.create(homeFeaturedChildDTO,productId );
            return "redirect:/admin/home-page/" ;
        } catch (Exception ex) {
            return "redirect:/admin/home-page/";
        }
    }

    @GetMapping("delete/{id}")
    public String departmentDelete(@PathVariable(value="id") Long id) {


        try {

            homeFeaturedService.deleteById(id);
            return "redirect:/admin/home-page/" ;
        } catch (Exception ex) {
            return "redirect:/admin/home-page/";
        }
    }

}
