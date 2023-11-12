package com.example.springboot_101.Controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.*;

import com.example.springboot_101.Controller.ProductController;
import com.example.springboot_101.Entity.Product;
import com.example.springboot_101.Service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void testShowAddForm() throws Exception {
        mockMvc.perform(get("/Products/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-product"))
                .andExpect(model().attributeExists("product"));
    }

    @Test
    public void testSaveProduct() throws Exception {
        Product mockProduct = new Product(1L, "Product1", 100);
        when(productService.save(any(Product.class))).thenReturn(mockProduct); // Mock the behavior

        mockMvc.perform(post("/Products")
                        .param("id", "1")
                        .param("designation", "Product1")
                        .param("prix", "100"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/Products"));

        verify(productService, times(1)).save(any(Product.class));
    }

    @Test
    public void testShowEditForm() throws Exception {
        when(productService.findById(1L)).thenReturn(Optional.of(new Product(1L, "Product1", 100)));

        mockMvc.perform(get("/Products/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("update-product"))
                .andExpect(model().attributeExists("product"));
    }

    @Test
    public void testUpdateProduct() throws Exception {
        mockMvc.perform(post("/Products/update/1")
                        .param("id", "1")
                        .param("designation", "UpdatedProduct")
                        .param("prix", "150"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/Products"));

        verify(productService, times(1)).save(any(Product.class));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        when(productService.findById(1L)).thenReturn(Optional.of(new Product(1L, "Product1", 100)));

        mockMvc.perform(get("/Products/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/Products"));

        verify(productService, times(1)).deleteById(1L);
    }
}
