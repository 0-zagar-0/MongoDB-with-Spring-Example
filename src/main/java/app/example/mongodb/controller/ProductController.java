package app.example.mongodb.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import app.example.mongodb.model.Product;
import app.example.mongodb.service.product.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Product save(@RequestBody Product product) {
        return productService.save(product);
    }

    @GetMapping(value = "/{id}")
    public Product getById(@PathVariable String id) {
        return productService.getById(id);
    }

    @PutMapping(value = "/{id}")
    public Product updateById(@PathVariable String id, @RequestBody Product product) {
        return productService.updateById(id, product);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable String id) {
        productService.deleteById(id);
    }
}
