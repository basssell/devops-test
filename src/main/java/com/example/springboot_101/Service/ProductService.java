package com.example.springboot_101.Service;

import com.example.springboot_101.Entity.Product;
import com.example.springboot_101.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository repo;
    public List<Product> lister() {

        return repo.findAll();
    }
    public Product save(Product produit){
        return repo.save(produit);
    }
    public Optional<Product> findById(Long id){
        return repo.findById(id);
    }
    public void deleteById(Long id){
        repo.deleteById(id);
    }

}
