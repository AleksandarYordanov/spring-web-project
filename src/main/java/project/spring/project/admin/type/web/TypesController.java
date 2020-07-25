package project.spring.project.admin.type.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import project.spring.project.admin.category.model.CategoryDTO;
import project.spring.project.admin.category.service.CategoryService;
import project.spring.project.admin.product.model.ProductDTO;
import project.spring.project.admin.product.service.ProductService;
import project.spring.project.admin.type.model.TypeDTO;
import project.spring.project.admin.type.service.TypeService;

import java.util.List;

@Controller
@RequestMapping("/admin/types")
public class TypesController {

    private final TypeService typeService;
    private final ProductService productService;


    public TypesController(TypeService typeService, ProductService productService) {
        this.typeService = typeService;
        this.productService = productService;
           }

    @GetMapping("details/{id}")
    public ModelAndView typeDetails(@PathVariable(value="id") Long id) {
        ModelAndView modelAndView = new ModelAndView("admin/type/type-details");
       TypeDTO type = typeService.getById(id);
        modelAndView.addObject(type);
        List<ProductDTO> allProducts = productService.getAll();
        modelAndView.addObject("allProducts",allProducts);
        modelAndView.addObject(id);
        return modelAndView;
    }

    @PostMapping("details/{id}")
    public String departmentDetailsSave(@ModelAttribute TypeDTO typeDTO, @RequestParam("typeId") List<Long> typesIds
            , @PathVariable(value="id") Long id) {
        typeDTO.setId(id);

        try {

            typeService.update(typeDTO,typesIds);
            return "redirect:/admin/types/" ;
        } catch (Exception ex) {
            return "redirect:/admin/types/";
        }
    }

    @GetMapping()
    public ModelAndView department(Model model) {
        ModelAndView modelAndView = new ModelAndView("admin/type/types");
       List<TypeDTO>  typeDTOS =typeService.getAll();
       modelAndView.addObject(typeDTOS);
       return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView departmentAdd() {
        ModelAndView modelAndView  = new ModelAndView("admin/type/type-create");
        List<ProductDTO> allProducts = productService.getAll();
        modelAndView.addObject("allProducts",allProducts);
        return modelAndView;
    }

    @PostMapping("/create")
    public String createDepartment(@ModelAttribute TypeDTO typeDTO, @RequestParam("productId") List<Long> typeIds) {

        try {

            typeService.create(typeDTO,typeIds);
            return "redirect:/admin/types/" ;
        } catch (Exception ex) {
            return "redirect:/admin/types/";
        }
    }

    @GetMapping("delete/{id}")
    public String departmentDelete(@PathVariable(value="id") Long id) {


        try {

            typeService.deleteById(id);
            return "redirect:/admin/types/" ;
        } catch (Exception ex) {
            return "redirect:/admin/types/";
        }
    }
}
