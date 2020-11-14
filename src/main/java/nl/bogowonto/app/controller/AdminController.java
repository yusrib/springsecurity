package nl.bogowonto.app.controller;

import nl.bogowonto.app.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    private final UserRepository userRepository;

    public AdminController(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/admin")
    public String adminPage(final ModelMap model) {
        model.addAttribute("users", userRepository.findAll());
        return "admin/users-overview";
    }

}
