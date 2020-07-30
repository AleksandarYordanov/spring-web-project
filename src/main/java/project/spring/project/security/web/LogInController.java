package project.spring.project.security.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.Ordered;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import project.spring.project.admin.product.model.ProductChildDTO;

@Controller
public class LogInController  {

    @GetMapping("/login")
    public String login() {
        return "auth/sign-in";
    }

    @PostMapping("/login-error")
    public ModelAndView onLoginError(
            @ModelAttribute(UsernamePasswordAuthenticationFilter.
                    SPRING_SECURITY_FORM_USERNAME_KEY) String email
    ) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("error", "bad.credentials");
        modelAndView.addObject("username", email);
        modelAndView.setViewName("auth/sign-in");

        return modelAndView;
    }
}
