package com.maxPotential.MaxxEnergy.Web.Controller;

import com.maxPotential.MaxxEnergy.Business.Model.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.unbescape.html.HtmlEscape;


@Controller
public class LegacyController {





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

    /** User zone index. */
    @RequestMapping("/user/index")
    public String userIndex() {
        return "user/index";
    }

    /** Administration zone index. */
    @RequestMapping("/admin")
    public String adminIndex() {
        return "admin";
    }


    /** Login form. */
    @RequestMapping("/login")
    public String login()
    {
        return "login";
    }

    /** Login form with error attribute added.. */
    @RequestMapping("/login-error")
    public String loginError(Model model)
    {
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


}


