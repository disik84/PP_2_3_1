package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;

@Controller
public class IndexUsers {

    UserService userService;

    public IndexUsers(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String mainPage(ModelMap model) {
        model.addAttribute("listUsers", userService.listUsers());
        return "index";
    }

    @GetMapping(value = "/adduser")
    public String addUser(ModelMap model) {
        return "adduser";
    }

    @GetMapping(value = "/newuser")
    public String newUser(@RequestParam(value = "name", required = false) String name,
                          @RequestParam(value = "lastname", required = false) String lastname,
                          @RequestParam(value = "age", required = false) byte age,
                          ModelMap model) {
        User user = new User(name, lastname, age);
        userService.add(user);
        model.addAttribute("user", user);
        return "newuser";
    }

    @GetMapping(value = "/delete")
    public String deleteUser(@RequestParam(value = "id", required = false) Long id,
                             ModelMap model) {
        userService.delete(id);
        return "deleteuser";
    }

    @GetMapping(value = "/editoruser")
    public String editorUser(@RequestParam(value = "id", required = false) Long id,
                             ModelMap model) {
        model.addAttribute("user", userService.getUser(id));
        return "editoruser";
    }

    @GetMapping(value = "/edit")
    public String edit(@RequestParam(value = "id", required = false) Long id,
                       @RequestParam(value = "name", required = false) String name,
                       @RequestParam(value = "lastname", required = false) String lastName,
                       @RequestParam(value = "age", required = false) byte age,
                       ModelMap model) {
        User user = new User(name, lastName, age);
        userService.edit(id, name, lastName, age);
        return "edit";
    }
}
