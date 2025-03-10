package project.spring.project.admin.department.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import project.spring.project.admin.category.model.CategoryChildDTO;
import project.spring.project.admin.category.service.CategoryService;
import project.spring.project.admin.department.model.DepartmentChildDTO;
import project.spring.project.admin.department.model.DepartmentSelfDTO;
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
    public ModelAndView departmentDetails(@PathVariable(value="id") Long id) {
        ModelAndView modelAndView = new ModelAndView("admin/department/department-details");
       DepartmentChildDTO department = departmentService.getById(id);
        modelAndView.addObject(department);
        List<CategoryChildDTO> allCategories = categoryService.getAll();
        modelAndView.addObject("allCategories",allCategories);
        modelAndView.addObject(id);
        return modelAndView;
    }

    @PostMapping("details/{id}")
    public String departmentDetailsSave(@ModelAttribute DepartmentSelfDTO department,@RequestParam("categoryId") List<Long> categoryIds
            ,@PathVariable(value="id") Long id) {
        department.setId(id);

        try {

            departmentService.update(department,categoryIds);
            return "redirect:/admin/departments/" ;
        } catch (Exception ex) {
            return "redirect:/admin/departments/";
        }
    }

    @GetMapping()
    public ModelAndView department(Model model) {
        ModelAndView modelAndView = new ModelAndView("admin/department/departments");
       List<DepartmentChildDTO>  departmentDTOS =departmentService.getAll();
       modelAndView.addObject(departmentDTOS);
       return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView departmentAdd() {
        ModelAndView modelAndView  = new ModelAndView("admin/department/department-create");
        List<CategoryChildDTO> allCategories = categoryService.getAll();
        modelAndView.addObject("allCategories",allCategories);
        return modelAndView;
    }

    @PostMapping("/create")
    public String createDepartment(@ModelAttribute DepartmentSelfDTO department,@RequestParam("categoryId") List<Long> categoryIds) {

        try {

            departmentService.create(department,categoryIds);
            return "redirect:/admin/departments/" ;
        } catch (Exception ex) {
            return "redirect:/admin/departments/";
        }
    }

    @GetMapping("delete/{id}")
    public String departmentDelete(@PathVariable(value="id") Long id) {


        try {

            departmentService.deleteById(id);
            return "redirect:/admin/departments/" ;
        } catch (Exception ex) {
            return "redirect:/admin/departments/";
        }
    }
}
