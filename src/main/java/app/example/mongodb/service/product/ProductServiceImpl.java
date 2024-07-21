package app.example.mongodb.service.product;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import app.example.mongodb.exception.EntityNotFoundException;
import app.example.mongodb.model.Product;
import app.example.mongodb.repository.product.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getById(String id) {
        return productRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find product by id: " + id)
        );
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product updateById(String id, Product product) {
        Product productFromDb = getById(id);
        Optional.ofNullable(product.getName())
                .ifPresent(productFromDb::setName);
        Optional.ofNullable(product.getDescription())
                .ifPresent(productFromDb::setDescription);
        Optional.ofNullable(product.getPrice())
                .ifPresent(productFromDb::setPrice);
        return productRepository.save(productFromDb);
    }

    @Override
    public void deleteById(String id) {
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException("Can't find product by id: " + id);
        }

        productRepository.deleteById(id);
    }
}
