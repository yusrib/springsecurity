package nl.bogowonto.app.controller;

import nl.bogowonto.app.models.User;
import nl.bogowonto.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping ("/")
    public String homePage(final ModelMap model) {
        return "index";
    }

    @GetMapping("/signup")
    public String showSignUpForm(final User user) {
        return "user/add-user";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login/login";
    }

    @GetMapping("/logout")
    public String logoutPage (final HttpServletRequest request, final HttpServletResponse response) {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "index";
    }

    @PostMapping("/user/adduser")
    public String addUser(@Valid final User user, final BindingResult result, final Model model) {
        if (result.hasErrors()) {
            return "user/add-user";
        }

        userRepository.save(user);
        model.addAttribute("user", user);
        return "main/hello";
    }

    @GetMapping("/user/edit/{id}")
    public String showUpdateForm(@PathVariable("id") final long id, final Model model) {
        final User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        return "user/update-user";
    }

    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") final long id, @Valid final User user, final BindingResult result, final Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "user/update-user";
        }

        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        return "index";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") final long id, final Model model) {
        final User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);
        model.addAttribute("users", userRepository.findAll());
        return "index";
    }

    @GetMapping ("/user/authenticated")
    public String adminPage(final ModelMap model) {
        model.addAttribute("user", getPrincipal());
        return "main/hello";
    }

    private User getPrincipal() {
        String userName;
        final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }

        return userRepository.findByName(userName);
    }
}
