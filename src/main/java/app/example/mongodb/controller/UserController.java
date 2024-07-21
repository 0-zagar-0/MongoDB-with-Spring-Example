package app.example.mongodb.controller;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import app.example.mongodb.model.User;
import app.example.mongodb.service.user.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    @GetMapping(value = "/{id}")
    public User getById(@PathVariable String id) {
        return userService.getById(id);
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @PutMapping(value = "/{id}")
    public User updateById(@PathVariable String id, @Valid @RequestBody User user) {
        return userService.updateById(id, user);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable String id) {
        userService.deleteById(id);
    }
}
