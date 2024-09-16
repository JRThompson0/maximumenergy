package com.maxPotential.MaxxEnergy.Web.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PublicController
{
    @GetMapping("/index")
    public String index(Model model) {
        return "index";  // This is the view name (e.g., a Thymeleaf template called 'index.html')
    }
    @RequestMapping("/login")
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

    @GetMapping("favicon.ico")
    @ResponseBody
    void returnNoFavicon() {
    }
}
