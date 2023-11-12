package com.example.springboot_101.Service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.springboot_101.Entity.Product;
import com.example.springboot_101.Repository.ProductRepository;
import com.example.springboot_101.Service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLister() {
        Product product = new Product(1L, "Product1", 100);
        when(productRepository.findAll()).thenReturn(Arrays.asList(product));

        List<Product> products = productService.lister();

        assertNotNull(products);
        assertFalse(products.isEmpty());
        assertEquals(1, products.size());
        assertEquals(product, products.get(0));
    }

    @Test
    public void testSave() {
        Product product = new Product(1L, "Product1", 100);
        when(productRepository.save(product)).thenReturn(product);

        Product savedProduct = productService.save(product);

        assertNotNull(savedProduct);
        assertEquals(product.getId(), savedProduct.getId());
    }

    @Test
    public void testFindById() {
        Product product = new Product(1L, "Product1", 100);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Optional<Product> foundProduct = productService.findById(1L);

        assertTrue(foundProduct.isPresent());
        assertEquals(product.getId(), foundProduct.get().getId());
    }

    @Test
    public void testDeleteById() {
        doNothing().when(productRepository).deleteById(1L);

        productService.deleteById(1L);

        verify(productRepository, times(1)).deleteById(1L);
    }
}
