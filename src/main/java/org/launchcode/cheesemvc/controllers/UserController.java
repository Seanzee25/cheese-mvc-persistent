package org.launchcode.cheesemvc.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.launchcode.cheesemvc.models.User;
import org.launchcode.cheesemvc.models.UserData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("user")
public class UserController {

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addUser(Model model) {
        model.addAttribute(new User());
        return "user/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid User user,
                      Errors errors, @RequestParam String verify) {

        if(!isAlpha((String) errors.getFieldValue("username"))) {
            errors.rejectValue("username", "usernameNotAlpha", "Must contain letters only");
        }

        if(!errors.getFieldValue("password").equals(verify)) {
            errors.rejectValue("password", "passwordMismatch", "Passwords did not match");
        }

        if(errors.hasErrors()) {
            model.addAttribute("username", errors.getFieldValue("username"));
            model.addAttribute("email", errors.getFieldValue("email"));
            return "user/add";
        } else {
            UserData.add(user);
            model.addAttribute("username", user.getUsername());
            model.addAttribute("users", UserData.getAll());
            return "user/index";
        }
    }

    @RequestMapping(value="")
    public String index() {
        return "user/index";
    }

    @RequestMapping(value="/{userId}")
    public String displayUser(Model model, @PathVariable int userId) {
        User user = UserData.getById(userId);
        model.addAttribute("user", user);
        return "user/profile";
    }

    private boolean isAlpha(String string) {
        return string.matches("[a-zA-Z]+");
    }

}
