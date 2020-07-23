package project.spring.project.admin.product.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
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

        List<ProductDTO> allProducts = productService.getAll();

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
       Long id = productService.createAndReturnId(product);

        for (int i = 0; i < myParams.size(); i++) {
            String p = myParams.get(i);
            String location = getDestinationLocation(id,p.substring(p.length()/2,p.length()/2+8));
            String locationWeb = "";
            try {
            locationWeb = saveLocal(location,p);
            } catch (IOException e) {
                e.printStackTrace();
            }
            UploadFileDTO uploadFileDTO = new UploadFileDTO();
            uploadFileDTO.setLocation(locationWeb);

            uploadFileDTO.setPosition(i);
            uploadFileService.saveFileForProductId(uploadFileDTO,id);
        }

        return "redirect:/admin/products/";
    }

    private String saveLocal(String location, String base64string) throws IOException {

        String base64Image = base64string.split(",")[1];

        byte[] imageBytes = Base64.getDecoder()
                .decode(base64Image.getBytes(StandardCharsets.UTF_8));

        GoogleCloudStorageServiceImpl googleCloudStorageService = new GoogleCloudStorageServiceImpl();
      return (googleCloudStorageService.createSmth(imageBytes, location));



    }

    private String getDestinationLocation(Long productId, String name) {
        return "products/"+productId+"/"+name;
    }
    private String getDestinationFolderLocation(Long productId, String name) {
        return "D:/Programming/Projects/SoftUni/Java Web/Spring Project/upload-files/products/"+productId+"/";
    }

}
