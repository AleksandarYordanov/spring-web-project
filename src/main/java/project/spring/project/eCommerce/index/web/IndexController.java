package project.spring.project.eCommerce.index.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import project.spring.project.admin.department.model.DepartmentDTO;
import project.spring.project.admin.department.service.DepartmentService;

import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {
    private final DepartmentService departmentService;

    public IndexController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping()
    public ModelAndView dashboard(Model model) {
        ModelAndView modelAndView  = new ModelAndView("commerce/index/index");
        List<DepartmentDTO> departmentDTOList = departmentService.getAllWitchCategoryChildDTO();
        modelAndView.addObject("departmentDtoList",departmentDTOList);
        return modelAndView;
    }

}
