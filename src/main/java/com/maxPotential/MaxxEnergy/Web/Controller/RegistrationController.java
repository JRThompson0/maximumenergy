package com.maxPotential.MaxxEnergy.Web.Controller;

import com.maxPotential.MaxxEnergy.Model.User;
import com.maxPotential.MaxxEnergy.Repository.RoleRepository;
import com.maxPotential.MaxxEnergy.Web.Service.UserDetailsServiceImpl;
import com.maxPotential.MaxxEnergy.Web.Service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController
{
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Autowired
    UserRegistrationService userRegistrationService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, BindingResult result)
    {
        if (result.hasErrors())
        {
            return "register";
        }
        userRegistrationService.createNewUser(user);
        return "redirect:/registered";
    }
//    @PostMapping("/user/registration")
//    public ModelAndView registerUserAccount(
//            @ModelAttribute("user") @Valid UserDto userDto,
//            HttpServletRequest request,
//            Errors errors) {
//        try {
//            User registered = userService.registerNewUserAccount(userDto);
//        } catch (UserAlreadyExistException uaeEx) {
//            mav.addObject("message", "An account for that username/email already exists.");
//            return mav;
//        }
//        return new ModelAndView("successRegister", "user", userDto);
//    }
    @GetMapping("/register" )
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }
    @GetMapping("/registered")
    public String showRegisteredConfirmation(Model model)
    {
        model.addAttribute("registrationComplete",true);
        return "login";
    }
}

