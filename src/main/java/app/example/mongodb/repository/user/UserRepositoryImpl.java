package app.example.mongodb.repository.user;

import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import app.example.mongodb.model.User;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final MongoTemplate mongoTemplate;

    public UserRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public User save(User user) {
        return mongoTemplate.save(user);
    }

    @Override
    public Optional<User> findById(String id) {
        User user = mongoTemplate.findOne(getIdAndNotDeletedQuery(id), User.class);
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAll() {
        return mongoTemplate.find(
                new Query().addCriteria(Criteria.where("isDeleted").is(false)),
                User.class
        );
    }

    @Override
    public boolean existsByEmail(String email) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));
        return mongoTemplate.exists(query, User.class);
    }

    @Override
    public void deleteById(String id) {
        Update update = new Update();
        update.set("isDeleted", true);
        mongoTemplate.updateFirst(getIdAndNotDeletedQuery(id), update, User.class);
    }

    private Query getIdAndNotDeletedQuery(String id) {
        return new Query(Criteria.where("id").is(id).and("isDeleted").is(false));
    }
}
