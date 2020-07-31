package project.spring.project.eCommerce.order.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import project.spring.project.admin.department.service.DepartmentService;
import project.spring.project.admin.product.service.ProductService;
import project.spring.project.eCommerce.base.BaseController;
import project.spring.project.eCommerce.cart.service.CartService;
import project.spring.project.eCommerce.order.service.OrderService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {

    private final ProductService productService;
    private final OrderService orderService;



    public OrderController(CartService cartService, DepartmentService departmentService, ProductService productService, OrderService orderService) {
        super(cartService, departmentService);
        this.productService = productService;
        this.orderService = orderService;
    }

    @GetMapping("/details")
    public ModelAndView orderDetails(){
        ModelAndView modelAndView = new ModelAndView("commerce/checkout/details");
        modelAndView = setBasicModelAndView(modelAndView);
        return modelAndView;
    }


    @GetMapping("/review")
    public ModelAndView orderReview(){
        ModelAndView modelAndView = new ModelAndView("commerce/checkout/review");
        modelAndView = setBasicModelAndView(modelAndView);
        return modelAndView;
    }

    @GetMapping("/complete")
    public ModelAndView orderComplete(){
        ModelAndView modelAndView = new ModelAndView("commerce/checkout/review");
        modelAndView = setBasicModelAndView(modelAndView);

        return modelAndView;
    }




    @PostMapping("/complete")
    public String orderComplete( HttpServletRequest request,String email) {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
           orderService.createOrder(authentication,email);

        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;

    }


}
