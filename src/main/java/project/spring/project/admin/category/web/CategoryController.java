package project.spring.project.admin.category.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.spring.project.admin.category.model.CategoryDTO;
import project.spring.project.admin.category.service.CategoryService;
import project.spring.project.admin.department.model.DepartmentDTO;
import project.spring.project.admin.department.service.DepartmentService;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {

    private final CategoryService categoryService;


    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public String categories(Model model){
        return "admin/category/categories";
    }

    @GetMapping("/create")
    public String categoriesAdd(Model model){
        return "admin/category/category-create";
    }

    @PostMapping("/create")
    public String createCategory(@ModelAttribute CategoryDTO categoryDTO){
        try{
            categoryService.create(categoryDTO);
            return "redirect:/admin/categories";
        }catch (Exception ex){
            return "redirect:/admin/categories";
        }
    }

}
