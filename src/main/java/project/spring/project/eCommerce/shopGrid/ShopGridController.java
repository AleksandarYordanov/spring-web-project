package project.spring.project.eCommerce.shopGrid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import project.spring.project.admin.department.service.DepartmentService;
import project.spring.project.admin.product.model.ProductChildDTO;
import project.spring.project.admin.type.model.TypeChildDTO;
import project.spring.project.admin.type.service.TypeService;
import project.spring.project.eCommerce.base.BaseController;
import project.spring.project.eCommerce.cart.service.CartService;

@Controller
@RequestMapping("type")
public class ShopGridController extends BaseController {

    private final TypeService typeService;

    public ShopGridController(CartService cartService, DepartmentService departmentService, TypeService typeService) {
        super(cartService, departmentService);
        this.typeService = typeService;
    }

    @GetMapping("{id}")
    public ModelAndView dashboard(@PathVariable(value="id") Long id) {
        ModelAndView modelAndView  = new ModelAndView("commerce/type/shop-grid");

        modelAndView = setBasicModelAndView(modelAndView);


        TypeChildDTO typeChildDTO = typeService.getById(id);
        modelAndView.addObject(typeChildDTO);

        return modelAndView;
    }
}
