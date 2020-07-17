package project.spring.project.admin.department.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.spring.project.admin.department.model.DepartmentDTO;
import project.spring.project.admin.department.service.DepartmentService;

@Controller
@RequestMapping("/admin/departments")
public class DepartmentsController {

    private final DepartmentService departmentService;

    public DepartmentsController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping()
    public String department(Model model) {
        return "admin/department/departments";
    }

    @GetMapping("/create")
    public String departmentAdd(Model model) {
        return "admin/department/department-create";
    }

    @PostMapping("/create")
    public String createDepartment(@ModelAttribute DepartmentDTO department) {
        System.out.println(department);
        System.out.println();
        try {
            departmentService.create(department);
            return "redirect:/admin/departments/" ;
        } catch (Exception ex) {
            return "redirect:/admin/departments/";
        }
    }
}
