package app.example.mongodb.service.product;

import java.util.List;
import app.example.mongodb.model.Product;

public interface ProductService {
    Product save(Product product);

    Product getById(String id);

    List<Product> getAll();

    Product updateById(String id, Product product);

    void deleteById(String id);
}
