package project.spring.project.eCommerce.base;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import project.spring.project.admin.department.model.DepartmentDTO;
import project.spring.project.admin.department.service.DepartmentService;
import project.spring.project.eCommerce.cart.model.CartChildDTO;
import project.spring.project.eCommerce.cart.service.CartService;

import java.util.List;

public class BaseController {

    protected final CartService cartService;
    protected final DepartmentService departmentService;

    public BaseController(CartService cartService, DepartmentService departmentService) {
        this.cartService = cartService;
        this.departmentService = departmentService;
    }


    protected ModelAndView setBasicModelAndView(ModelAndView modelAndView){
        List<DepartmentDTO> departmentDTOList = departmentService.getAllWitchCategoryChildDTO();
        modelAndView.addObject("departmentDtoList",departmentDTOList);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CartChildDTO cartChildDTO = cartService.getByAuthentication(authentication);
        modelAndView.addObject(cartChildDTO);

        return modelAndView;
    }
}
