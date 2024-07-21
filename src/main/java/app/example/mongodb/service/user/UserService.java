package app.example.mongodb.service.user;

import jakarta.validation.Valid;
import java.util.List;
import app.example.mongodb.model.User;

public interface UserService {
    User create(User user);

    User getById(String id);

    List<User> getAll();

    User updateById(String id, @Valid User user);

    void deleteById(String id);
}
