package com.zhm.product_management_restful_api.managing.application;

import com.zhm.product_management_restful_api.api.ProductDTO;
import com.zhm.product_management_restful_api.managing.domain.Product;
import com.zhm.product_management_restful_api.managing.infrastructure.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductApplicationService {

    private ProductRepository productRepository;

    @Autowired
    public ProductApplicationService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void createProduct(ProductDTO productDTO) {
        if(productDTO.getName() == null || productDTO.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if(productDTO.getPrice() == null) {
            throw new IllegalArgumentException("Price cannot be null");
        }
        if(productDTO.getStockQuantity() == null) {
            throw new IllegalArgumentException("Stock quantity cannot be null");
        }
        if(productDTO.getCategory() == null || productDTO.getCategory().trim().isEmpty()) {
            throw new IllegalArgumentException("Category cannot be null or empty");
        }
        Product product = new Product(productDTO.getName(), productDTO.getDescription(), productDTO.getPrice(), productDTO.getStockQuantity(), productDTO.getCategory());
        productRepository.save(product);
    }

    public void updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setStockQuantity(productDTO.getStockQuantity());
        product.setCategory(productDTO.getCategory());
        product.setUpdatedAt(LocalDateTime.now());
        productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public void updateStockQuantity(Long id, Integer newQuantity) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        product.updateStock(newQuantity);
        product.setUpdatedAt(LocalDateTime.now());
        productRepository.save(product);
    }
}
