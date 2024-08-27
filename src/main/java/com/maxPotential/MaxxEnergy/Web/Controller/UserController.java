package com.maxPotential.MaxxEnergy.Web.Controller;

import com.maxPotential.MaxxEnergy.Business.Model.User;
import com.maxPotential.MaxxEnergy.Business.Repository.RoleRepository;
import com.maxPotential.MaxxEnergy.Business.Repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.unbescape.html.HtmlEscape;

import java.util.Locale;


@Controller
public class UserController {

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Autowired
    public UserRepository uRepository;

    @Autowired
    public RoleRepository rRepository;
    @GetMapping(path="/login")
    public String loginString()
    {
        return "login";
    }

    @GetMapping(path="/")
    public String root(Locale locale) {
        return "index";
    }


    @GetMapping(path="/admin")
    public String adminString() {
        return "admin";
    }

    @GetMapping("/register")
    public String showNewUsersForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/add")
    public String saveUsers(@ModelAttribute("user") User user) throws Exception {
        String tempEmailId = user.getEmail();
        if (tempEmailId != null && !tempEmailId.isEmpty()) {
            User userObject = uRepository.findByEmail(tempEmailId);
            if (userObject != null) {
                throw new Exception("User with " + tempEmailId + " is already exist");
            }

        }
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        user.setRoles(rRepository.findByRoleName("USERS"));
        uRepository.save(user);
        return "redirect:/login";
    }

    @GetMapping("/register/admin")
    public String showADMINUsersForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "adminRegister";
    }
    @GetMapping("/register/employee")
    public String showEMPLOYEEUsersForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "employeeRegister";
    }
    @PostMapping("/add/employee")
    public String saveEMPLOYEEUsers(@ModelAttribute("user") User user) {
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        user.setRoles(rRepository.findByRoleName("EMPLOYEE"));
        uRepository.save(user);
        return "redirect:/login";
    }
    @PostMapping("/add/admin")
    public String saveADMINUsers(@ModelAttribute("user") User user) {
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        user.setRoles(rRepository.findByRoleName("ADMIN"));
        uRepository.save(user);
        return "redirect:/login";
    }

    /** Home page. */
    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    /** User zone index. */
    @RequestMapping("/user/index")
    public String userIndex() {
        return "user/index";
    }

    /** Administration zone index. */
    @RequestMapping("/admin/index")
    public String adminIndex() {
        return "admin/index";
    }

    /** Shared zone index. */
    @RequestMapping("/shared/index")
    public String sharedIndex() {
        return "shared/index";
    }

    /** Login form. */
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    /** Login form with error. */
    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    /** Simulation of an exception. */
    @RequestMapping("/simulateError")
    public void simulateError() {
        throw new RuntimeException("This is a simulated error message");
    }

    /** Error page. */
    @RequestMapping("/error")
    public String error(HttpServletRequest request, Model model) {
        model.addAttribute("errorCode", "Error " + request.getAttribute("javax.servlet.error.status_code"));
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        StringBuilder errorMessage = new StringBuilder();
        errorMessage.append("<ul>");
        while (throwable != null) {
            errorMessage.append("<li>").append(HtmlEscape.escapeHtml5(throwable.getMessage())).append("</li>");
            throwable = throwable.getCause();
        }
        errorMessage.append("</ul>");
        model.addAttribute("errorMessage", errorMessage.toString());
        return "error";
    }

    /** Error page. */
    @RequestMapping("/403")
    public String forbidden() {
        return "403";
    }
    @GetMapping("/favicon.ico")
    public String favicon()
}


