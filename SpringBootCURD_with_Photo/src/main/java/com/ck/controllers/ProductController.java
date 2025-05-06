package com.ck.controllers;

import com.ck.Service.CategoryService;
import com.ck.config.FileUploadUtil;
import com.ck.models.Categories;
import com.ck.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import com.ck.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public String viewHomePage(Model model){
        return findPaginated(1, "id" , "asc" , model);
    }

    @RequestMapping("index")
    public String Project1(Model model , @Param("keyword") String keyword){
        List<Product> listProduct = productService.getAllProduct(keyword);
        model.addAttribute("listProduct" , listProduct);
        model.addAttribute("keyword" , keyword);
        return "index";
    }

    @GetMapping("/showNewProductForm")
    public String Project2(Model model) {
        // create model attribute to bind form data
        Product product = new Product();
        List<Categories> categories = categoryService.getAllCategory();
        model.addAttribute("product" , product);
        model.addAttribute("categories" , categories);
        return "Add_product";
    }

    @PostMapping("/saveProduct")
    public RedirectView saveProduct(@ModelAttribute("product") Product product,
                                    @RequestParam("image") MultipartFile multipartFile) throws IOException {
        if (!multipartFile.isEmpty()) { // Nếu có ảnh mới được chọn
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            product.setPhotos(fileName);
            Product savedProduct = productService.saveProduct(product);

            String uploadDir = "product-photos/" + savedProduct.getId();
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        } else { // Nếu không có ảnh mới, giữ nguyên ảnh cũ
            Product existingProduct = productService.getProductById(product.getId());
            product.setPhotos(existingProduct.getPhotos());
            productService.saveProduct(product);
        }

        return new RedirectView("/", true);
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String UpdateImage(@PathVariable(value = "id") long id , Model model) {
        Product product = productService.getProductById(id);
        List<Categories> categories = categoryService.getAllCategory();
        model.addAttribute("product" , product);
        model.addAttribute("categories" , categories);
        return "Edit_product";
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable(value = "id") long id) {
        this.productService.deleteProductById(id);
        return "redirect:/";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 3 ;
        Page<Product> page = productService.findPaginated(pageNo , pageSize , sortField , sortDir);
        List<Product> listProduct = page.getContent();

        model.addAttribute("currentPage" , pageNo);
        model.addAttribute("totalPages" , page.getTotalPages());
        model.addAttribute("totalItems" , page.getTotalElements());

        model.addAttribute("sortField" , sortField);
        model.addAttribute("sortDir" , sortDir);
        model.addAttribute("reverseSortDir" , sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("listProduct" , listProduct);

        return "index";
    }

}
