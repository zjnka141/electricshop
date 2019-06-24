package com.vuidoan.electricshop.controller;

import com.vuidoan.electricshop.dto.ProductDTO;
import com.vuidoan.electricshop.dto.ProductMapper;
import com.vuidoan.electricshop.model.Category;
import com.vuidoan.electricshop.model.Producer;
import com.vuidoan.electricshop.model.Product;
import com.vuidoan.electricshop.repository.CategoryRepository;
import com.vuidoan.electricshop.repository.ProducerRepository;
import com.vuidoan.electricshop.repository.ProductRepository;
import com.vuidoan.electricshop.validation.Create;
import com.vuidoan.electricshop.validation.Update;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProducerRepository producerRepository;
    ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);
    @GetMapping("")
    public String home(Model model){
        model.addAttribute("products",productRepository.findAll());
        return "home";
    }
    @GetMapping("/manager")
    public String manager(Model model){
        List<Product> products=productRepository.findAll();
        model.addAttribute("products",products);
        model.addAttribute("categories",categoryRepository.findAll());
        model.addAttribute("producers",producerRepository.findAll());
        model.addAttribute("tabManager",true);
        return "manager";
    }
    @GetMapping("/add")
    public String addProduct(Model model){
        addProductAttribute(model);
        model.addAttribute("newProduct",new ProductDTO());
        model.addAttribute("action","add");
        return "form-product";
    }
    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<?> addProduct(@Validated @RequestBody ProductDTO productDTO,
                                     BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<List>(bindingResult.getAllErrors(), HttpStatus.OK);
        }
        productRepository.save(productMapper.toProduct(productDTO));
//        return "redirect:/products/manager";
        return new ResponseEntity<>("Success",HttpStatus.OK);
    }
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id){
        productRepository.delete(productRepository.getOne(id));
        return "redirect:/products/manager";
    }
    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable("id") int id,Model model){
        Product product=productRepository.findById(id).orElse(null);
        ProductDTO productDTO=productMapper.toProductDTO(product);
        productDTO.setId(id);
        model.addAttribute("newProduct",productDTO);
        model.addAttribute("edit",true);
        model.addAttribute("action","edit");
        addProductAttribute(model);
        return "form-product";
    }
    @PostMapping("/edit")
    public String editProduct(@Validated @ModelAttribute("newProduct") ProductDTO productDTO, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            addProductAttribute(model);
            model.addAttribute("action","add");
            return "form-product";
        }
        Product product=productMapper.toProduct(productDTO);
        product.setId(productDTO.getId());
        productRepository.save(product);
        return "redirect:/products/manager";

    }
    @GetMapping(value="/search",params = "q")
    public String searchProduct(@RequestParam("q") String name,Model model){
        List<Product> products=productRepository.findByNameContainingIgnoreCase(name);
        model.addAttribute("products",products);
        return "/manager";
    }
    public void addProductAttribute(Model model){
        List<Producer> producers=producerRepository.findAll();
        List<Category> categories=categoryRepository.findAll();
        model.addAttribute("categories",categories);
        model.addAttribute("producers",producers);
    }
}
