package com.example.springboot_101.Controller;

import com.example.springboot_101.Entity.Product;
import com.example.springboot_101.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Products")

public class ProductController {
    @Autowired
    ProductService proService;

    @GetMapping
    public String getAll(Model model) {
        List<Product> products = proService.lister();
        model.addAttribute("products", products);
        return "listerProduits";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        Product p = new Product() ;
        p.setId(0);
        model.addAttribute("product", p);
        return "add-product";
    }

    @PostMapping
    public String saveProduct(@ModelAttribute("product") Product product) {
        Product p =proService.save(product);
        return "redirect:/Products";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        Product product = proService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        model.addAttribute("product", product);
        return "update-product";
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable("id") long id, @ModelAttribute("product") Product product,
                                BindingResult result, Model model) {

        if (result.hasErrors()) {
            product.setId(id);
            return "update-product";
        }
        proService.save(product);
        return "redirect:/Products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") long id, Model model) {
        Product product = proService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        proService.deleteById(id);
        return "redirect:/Products";
    }

//@GetMapping
//public List<Product> getAll(){
//    return proService.lister();
//}
//@PostMapping
//    public Product createProduct(@RequestBody Product product){
//        return proService.save(product);
//    }
//    @GetMapping("/{id}")
//    public Optional<Product> getProduct(@PathVariable Long id){
//        return proService.findById(id);
//    }
//@PutMapping("/{id}")
//    public Product updateProduct(@PathVariable Long id, @RequestBody Product product){
//    return proService.save(product);
//}
//
//@DeleteMapping("/{id}")
//public void deleteProduct(@PathVariable Long id){
//    proService.deleteById(id);
//}
}
