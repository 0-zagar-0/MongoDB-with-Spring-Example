package app.example.mongodb.repository.product;

import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import app.example.mongodb.model.Product;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private final MongoTemplate mongoTemplate;

    public ProductRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Product save(Product product) {
        return mongoTemplate.save(product);
    }

    @Override
    public Optional<Product> findById(String id) {
        Product product = mongoTemplate.findOne(getIdAndNotDeletedQuery(id), Product.class);
        return Optional.ofNullable(product);
    }

    @Override
    public List<Product> findAll() {
        Query query = new Query();
        query.addCriteria(Criteria.where("isDeleted").is(false));
        return mongoTemplate.find(query, Product.class);
    }

    @Override
    public boolean existsById(String id) {
        return mongoTemplate.exists(getIdAndNotDeletedQuery(id), Product.class);
    }

    @Override
    public void deleteById(String id) {
        Update update = new Update();
        update.set("isDeleted", true);
        mongoTemplate.updateFirst(getIdAndNotDeletedQuery(id), update, Product.class);
    }

    private Query getIdAndNotDeletedQuery(String id) {
        return new Query(Criteria.where("id").is(id).and("isDeleted").is(false));
    }
}
