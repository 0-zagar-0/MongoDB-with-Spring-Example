package app.example.mongodb.repository.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;
import java.util.Optional;
import app.example.mongodb.model.User;

public interface UserRepository {
    User save(User user);

    Optional<User> findById(String id);

    List<User> findAll();

    boolean existsByEmail(@Email @NotBlank @Size(min = 3, max = 50) String email);

    void deleteById(String id);
}
