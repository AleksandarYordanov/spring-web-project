package project.spring.project.eCommerce.singleProduct.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import project.spring.project.admin.department.model.DepartmentDTO;
import project.spring.project.admin.department.service.DepartmentService;
import project.spring.project.admin.product.model.ProductChildDTO;
import project.spring.project.admin.product.service.ProductService;
import project.spring.project.eCommerce.base.BaseController;
import project.spring.project.eCommerce.cart.model.CartChildDTO;
import project.spring.project.eCommerce.cart.service.CartService;

import java.util.List;

@Controller
@RequestMapping("product")
public class SingleProductController extends BaseController {

       private final ProductService productService;


    public SingleProductController(CartService cartService, DepartmentService departmentService, ProductService productService) {
        super(cartService, departmentService);
        this.productService = productService;
    }


    @GetMapping("{id}")
        public ModelAndView dashboard(@PathVariable(value="id") Long id) {
            ModelAndView modelAndView  = new ModelAndView("commerce/single-product/single-product");

        modelAndView = setBasicModelAndView(modelAndView);
        

           ProductChildDTO productChildDTO = productService.getById(id);
            modelAndView.addObject(productChildDTO);

            return modelAndView;
        }
}
