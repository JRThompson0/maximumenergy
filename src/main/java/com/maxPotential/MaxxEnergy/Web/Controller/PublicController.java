package com.maxPotential.MaxxEnergy.Web.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PublicController
{
    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("message", "Welcome to the home page!");
        return "index";  // This is the view name (e.g., a Thymeleaf template called 'index.html')
    }

}
