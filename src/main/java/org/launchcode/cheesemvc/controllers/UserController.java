package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.User;
import org.launchcode.cheesemvc.models.UserData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("user")
public class UserController {

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addUser() {
        return "user/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute User user, @RequestParam String verify) {
        boolean valid = true;
        if(!validateLength(user.getUsername(), 5, 15)) {
            model.addAttribute("usernameError", "Username must be between 5 - 15 letters");
            valid = false;
        }
        if(user.getEmail().isEmpty()) {
            model.addAttribute("emailEmpty", "Required");
            valid = false;
        }
        if(user.getPassword().isEmpty()) {
            model.addAttribute("passwordEmpty", "Required");
            valid = false;
        }
        if(!isAlpha(user.getUsername())) {
            model.addAttribute("usernameNotAlpha", "Must contain only letters");
            valid = false;
        }
        if(!user.getPassword().equals(verify)) {
            model.addAttribute("passwordMismatch", "Passwords didn't match");
        }

        if(valid) {
            model.addAttribute("username", user.getUsername());
            model.addAttribute("users", UserData.getAll());
            UserData.add(user);
            return "user/index";
        } else {
            model.addAttribute("username", user.getUsername());
            model.addAttribute("email", user.getEmail());
            return "user/add";
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

    private boolean validateLength(String string, int min, int max) {
        if(string.length() > max || string.length() < min) {
            return false;
        }
        return true;
    }

    private boolean isAlpha(String string) {
        return string.matches("[a-zA-Z]+");
    }

}
