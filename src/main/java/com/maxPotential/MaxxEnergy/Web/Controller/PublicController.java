package com.maxPotential.MaxxEnergy.Web.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.unbescape.html.HtmlEscape;

@Controller
public class PublicController
{
    @GetMapping("/index")
    public String index(Model model) {
        return "index";  // This is the view name (e.g., a Thymeleaf template called 'index.html')
    }
    @RequestMapping("/login.html")
    public String login(Model model)
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
    @RequestMapping("/logout")
    public String logout()
    {
        return "logout";
    }
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
    @GetMapping("favicon.ico")
    @ResponseBody
    void returnNoFavicon() {
    }
}
