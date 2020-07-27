package project.spring.project.admin.product.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import project.spring.project.admin.product.model.ProductChildDTO;
import project.spring.project.admin.product.model.ProductDTO;
import project.spring.project.admin.product.service.ProductService;
import project.spring.project.admin.utils.googleCloudStorage.GoogleCloudStorageServiceImpl;
import project.spring.project.admin.utils.fileUpload.UploadFileDTO;
import project.spring.project.admin.utils.fileUpload.UploadFileService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/admin/products")
public class ProductController {

    private final ProductService productService;
    private final UploadFileService uploadFileService;

    public ProductController(ProductService productService, UploadFileService uploadFileService) {
        this.productService = productService;
        this.uploadFileService = uploadFileService;
    }


    @GetMapping()
    public ModelAndView products(Model model) {


        ModelAndView modelAndView  = new ModelAndView("admin/products/products");

        List<ProductChildDTO> allProducts = productService.getAll();

        modelAndView.addObject("allProducts",allProducts);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView productAdd() {
        ModelAndView modelAndView  = new ModelAndView("admin/products/product-create");
        return modelAndView;
    }

    @PostMapping("/create")
    public String createProduct(@RequestParam("imageBits") List<String> myParams,@ModelAttribute ProductDTO product) throws IOException {

       myParams.removeAll(Arrays.asList("",null));
       if (myParams.isEmpty()){
           return "redirect:/admin/products/";
       }

       productService.createProductWithImage(product,myParams);



        return "redirect:/admin/products/";
    }




}
