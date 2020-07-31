package project.spring.project.security.web;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import project.spring.project.user.model.UserDTO;
import project.spring.project.user.model.UserEntity;
import project.spring.project.user.security.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller

public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String registerUserAccount
            (@ModelAttribute("user") @Valid UserRegisterDTO userDto,
             HttpServletRequest request, Errors errors) {


            userService.createAndLoginUser(userDto.getEmail(),userDto.getPassword());

        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
        // rest of the implementation
    }
}
