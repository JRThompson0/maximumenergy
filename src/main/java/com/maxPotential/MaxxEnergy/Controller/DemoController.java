package com.maxPotential.MaxxEnergy.Controller;

import com.maxPotential.MaxxEnergy.Model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;  
import org.springframework.web.servlet.ModelAndView;  
import org.springframework.stereotype.Controller;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

@Controller  
public class DemoController 
{
    @RequestMapping(value="/sample", method=RequestMethod.GET)
    public ModelAndView save(@ModelAttribute User user)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user-data");
        modelAndView.addObject("user", user);
        return modelAndView;
    }
    @Controller
    public class WelcomeController {
        @RequestMapping("/welcome")
        public String loginMessage(){
            return "welcome";
        }
    }


}  