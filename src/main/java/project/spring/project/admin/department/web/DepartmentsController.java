package project.spring.project.admin.department.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import project.spring.project.admin.category.model.CategoryDTO;
import project.spring.project.admin.category.service.CategoryService;
import project.spring.project.admin.department.model.DepartmentDTO;
import project.spring.project.admin.department.service.DepartmentService;

import java.util.List;

@Controller
@RequestMapping("/admin/departments")
public class DepartmentsController {

    private final DepartmentService departmentService;
    private final CategoryService categoryService;

    public DepartmentsController(DepartmentService departmentService, CategoryService categoryService) {
        this.departmentService = departmentService;
        this.categoryService = categoryService;
    }

    @GetMapping("details/{id}")
    public ModelAndView departmentDetails(Model model) {
        ModelAndView modelAndView = new ModelAndView("admin/department/departments");
        List<DepartmentDTO>  departmentDTOS =departmentService.getAll();
        modelAndView.addObject(departmentDTOS);
        return modelAndView;
    }

    @GetMapping()
    public ModelAndView department(Model model) {
        ModelAndView modelAndView = new ModelAndView("admin/department/departments");
       List<DepartmentDTO>  departmentDTOS =departmentService.getAll();
       modelAndView.addObject(departmentDTOS);
       return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView departmentAdd() {
        ModelAndView modelAndView  = new ModelAndView("admin/department/department-create");
        List<CategoryDTO> allCategories = categoryService.getAll();
        modelAndView.addObject("allCategories",allCategories);
        return modelAndView;
    }

    @PostMapping("/create")
    public String createDepartment(@ModelAttribute DepartmentDTO department,@RequestParam("categoryId") List<Long> categoryIds) {

        try {

            departmentService.create(department,categoryIds);
            return "redirect:/admin/departments/" ;
        } catch (Exception ex) {
            return "redirect:/admin/departments/";
        }
    }
}
