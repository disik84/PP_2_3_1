package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    void add(User user);
    void delete(Long id);
    List<User> listUsers();
    User getUser(Long id);
    void edit(Long id, String name, String lastName, byte age);
}
