package project.spring.project.admin.category.web;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import project.spring.project.admin.category.service.CategoryService;
import project.spring.project.admin.category.model.CategoryDTO;
import project.spring.project.admin.type.model.TypeDTO;
import project.spring.project.admin.type.service.TypeService;

import java.util.List;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {
  private final CategoryService categoryService;
  private final TypeService typeService;

    public CategoryController(CategoryService categoryService, TypeService typeService) {
        this.categoryService = categoryService;
        this.typeService = typeService;
    }


    @GetMapping("details/{id}")
    public ModelAndView categoryDetails(@PathVariable(value="id") Long id) {
        ModelAndView modelAndView = new ModelAndView("admin/category/category-details");
        CategoryDTO category = categoryService.getById(id);
        modelAndView.addObject(category);
        List<TypeDTO> allTypes = typeService.getAll();
        modelAndView.addObject("allTypes",allTypes);
        modelAndView.addObject(id);
        return modelAndView;
    }

    @PostMapping("details/{id}")
    public String departmentDetailsSave(@ModelAttribute CategoryDTO categoryDTO, @RequestParam("typeId") List<Long> typesIds
            , @PathVariable(value="id") Long id) {
        categoryDTO.setId(id);

        try {

            categoryService.update(categoryDTO,typesIds);
            return "redirect:/admin/category/categories" ;
        } catch (Exception ex) {
            return "redirect:/admin/category/categories";
        }
    }

    @GetMapping()
    public ModelAndView department(Model model) {
        ModelAndView modelAndView = new ModelAndView("admin/category/categories");
        List<CategoryDTO>  categoryDTOS =categoryService.getAll();
        modelAndView.addObject(categoryDTOS);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView departmentAdd() {
        ModelAndView modelAndView  = new ModelAndView("admin/category/category-create");
        List<TypeDTO> allTypes = typeService.getAll();
        modelAndView.addObject("allTypes",allTypes);
        return modelAndView;
    }

    @PostMapping("/create")
    public String createDepartment(@ModelAttribute CategoryDTO categoryDTO, @RequestParam("typeId") List<Long> typeIds) {

        try {

            categoryService.create(categoryDTO,typeIds);
            return "redirect:/admin/category/categories";
        } catch (Exception ex) {
            return "redirect:/admin/category/categories";
        }
    }

    @GetMapping("delete/{id}")
    public String departmentDelete(@PathVariable(value="id") Long id) {


        try {

            categoryService.deleteById(id);
            return "redirect:/admin/category/categories";
        } catch (Exception ex) {
            return "redirect:/admin/category/categories";
        }
    }
}
