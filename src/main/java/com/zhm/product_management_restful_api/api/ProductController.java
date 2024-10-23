package com.zhm.product_management_restful_api.api;

import com.zhm.product_management_restful_api.managing.application.ProductApplicationService;
import com.zhm.product_management_restful_api.managing.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductApplicationService productApplicationService;

    @Autowired
    public ProductController(ProductApplicationService productApplicationService) {
        this.productApplicationService = productApplicationService;
    }

    @GetMapping
    public List<Product> getProducts() {
        return productApplicationService.getProducts();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productApplicationService.getProduct(id);
    }

    @PostMapping
    public void createProduct(@RequestBody ProductDTO productDTO) {
        productApplicationService.createProduct(productDTO);
    }

    @PutMapping("/{id}")
    public void updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        productApplicationService.updateProduct(id, productDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productApplicationService.deleteProduct(id);
    }

    @PatchMapping("/{id}/update-stock")
    public void updateStock(@PathVariable Long id, @RequestBody Integer newQuantity) {
        productApplicationService.updateStockQuantity(id, newQuantity);
    }
}
