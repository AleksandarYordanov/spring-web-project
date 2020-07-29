package project.spring.project.eCommerce.singleProduct.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import project.spring.project.admin.department.model.DepartmentDTO;
import project.spring.project.admin.homePage.homeFeatured.model.HomeFeaturedChildDTO;
import project.spring.project.admin.homePage.promoBanners.model.PromoBannerChildDTO;
import project.spring.project.admin.homePage.topLefDeals.model.TopLeftDealChildDTO;
import project.spring.project.admin.homePage.topSlider.model.TopSliderChildDTO;
import project.spring.project.admin.product.model.ProductChildDTO;
import project.spring.project.admin.product.service.ProductService;

import java.util.List;
@Controller
@RequestMapping("product")
public class SingleProductController {

       private final ProductService productService;

    public SingleProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("{id}")
        public ModelAndView dashboard(@PathVariable(value="id") Long id) {
            ModelAndView modelAndView  = new ModelAndView("commerce/single-product/single-product");
           ProductChildDTO productChildDTO = productService.getById(id);
            modelAndView.addObject(productChildDTO);
            return modelAndView;
        }
}
