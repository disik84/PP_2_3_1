package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    void addUser(User user);

    void deleteUser(Long id);

    List<User> getListUsers();

    User getUserById(Long id);

    void editUser(Long id, String name, String lastName, byte age);
}
