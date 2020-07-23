package project.spring.project.admin.category.web;

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
@RequestMapping("/admin/categories")
public class CategoryController {

    private final CategoryService categoryService;


    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(value = "/details/{id}")
    public ModelAndView buyItem(@PathVariable String id) {
        ModelAndView modelAndView  = new ModelAndView("admin/category/category-details");

       CategoryDTO categoryDTO= categoryService.getById(Long.parseLong(id));
        modelAndView.addObject(categoryDTO);

        return modelAndView;
    }

    @GetMapping()
    public ModelAndView categories(Model model){
        ModelAndView modelAndView  = new ModelAndView("admin/category/categories");

      List<CategoryDTO> categories = categoryService.getAll();
        modelAndView.addObject(categories);
        return modelAndView;
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
