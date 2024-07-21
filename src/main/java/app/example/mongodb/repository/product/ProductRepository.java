package app.example.mongodb.repository.product;

import java.util.List;
import java.util.Optional;
import app.example.mongodb.model.Product;

public interface ProductRepository {
    Product save(Product product);

    Optional<Product> findById(String id);

    List<Product> findAll();

    boolean existsById(String id);

    void deleteById(String id);
}
