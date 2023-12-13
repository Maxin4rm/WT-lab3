package wt3.web;

import wt3.data_access.dao.AuthDao;
import wt3.data_access.dto.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthDao authDao;

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
        try {
            User user = authDao.loginUser(email, password);
            session.setAttribute("user", user);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "login";
        }
        return "redirect:/films";
    }

    @GetMapping("/signup")
    public String getSignupPage() {
        return "registration";
    }

    @PostMapping("/signup")
    public String registration(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String re_password,
            Model model
    ) {
        if (!Objects.equals(password, re_password)) {
            return "registration";
        }

        String salt = BCrypt.gensalt(8);
        try {
            authDao.registerUser(name, email, BCrypt.hashpw(password, salt));
        } catch (Exception e) {
            //model.addAttribute("error", e.getMessage());
            return "registration";
        }
        model.addAttribute("message", "Successfully registered, you can login now");
        return "registration";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "login";
    }
}
