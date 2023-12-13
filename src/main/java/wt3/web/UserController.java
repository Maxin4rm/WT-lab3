package wt3.web;

import wt3.data_access.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserDao userDao;

    @GetMapping
    public String getUsersPage(Model model) {
        model.addAttribute("users", userDao.getUsers());
        return "admin";
    }

    @PostMapping("/{id}")
    public String updateUser(
            @PathVariable int id,
            @RequestParam int rating,
            @RequestParam boolean isBanned,
            Model model
    ) {
        userDao.updateUser(id, rating, isBanned);
        return this.getUsersPage(model);
    }
}
