package app.example.mongodb.service.user;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import app.example.mongodb.exception.EntityNotFoundException;
import app.example.mongodb.exception.RegistrationException;
import app.example.mongodb.model.User;
import app.example.mongodb.repository.user.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RegistrationException("Can't continue registration...");
        }
        return userRepository.save(user);
    }

    @Override
    public User getById(String id) {
        return userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find user by id: " + id)
        );
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User updateById(String id, User user) {
        User userFromDb = getById(id);
        Optional.ofNullable(user.getEmail())
                .ifPresent(userFromDb::setEmail);
        Optional.ofNullable(user.getPassword())
                .ifPresent(userFromDb::setPassword);
        Optional.ofNullable(user.getFirstName())
                .ifPresent(userFromDb::setFirstName);
        Optional.ofNullable(user.getLastName())
                .ifPresent(userFromDb::setLastName);
        return userRepository.save(user);
    }

    @Override
    public void deleteById(String id) {
        if (!userRepository.existsByEmail(id)) {
            throw new EntityNotFoundException("Can't find user by id: " + id);
        }

        userRepository.deleteById(id);
    }
}
