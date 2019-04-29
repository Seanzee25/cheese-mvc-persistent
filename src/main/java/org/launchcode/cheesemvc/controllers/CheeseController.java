package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.Category;
import org.launchcode.cheesemvc.models.Cheese;
import org.launchcode.cheesemvc.models.Menu;
import org.launchcode.cheesemvc.models.data.CategoryDao;
import org.launchcode.cheesemvc.models.data.CheeseDao;
import org.launchcode.cheesemvc.models.data.MenuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("cheese")
public class CheeseController {

    @Autowired
    private CheeseDao cheeseDao;
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private MenuDao menuDao;

    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("cheeses", cheeseDao.findAll());
        model.addAttribute("title", "My Cheeses");
        return "cheese/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        model.addAttribute(new Cheese());
        model.addAttribute("categories", categoryDao.findAll());
        return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@ModelAttribute @Valid Cheese newCheese,
                                       Errors errors, @RequestParam int categoryId, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("title", "Add Cheese");
            model.addAttribute("categories", categoryDao.findAll());
            return "cheese/add";
        }

        Category category = categoryDao.findOne(categoryId);
        newCheese.setCategory(category);
        cheeseDao.save(newCheese);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model) {
        model.addAttribute("cheeses", cheeseDao.findAll());
        model.addAttribute("title", "Remove Cheeses");
        return "cheese/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam int[] cheeseIds) {
        for(int cheeseId : cheeseIds) {
            Cheese cheese = cheeseDao.findOne(cheeseId);
            for(Menu menu : cheese.getMenus()) {
                menu.removeItem(cheese);
                menuDao.save(menu);
            }
            cheeseDao.delete(cheeseId);
        }
        return "redirect:";
    }

    @RequestMapping(value = "edit/{cheeseId}", method = RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable int cheeseId) {
        model.addAttribute("cheese", cheeseDao.findOne(cheeseId));
        model.addAttribute("categories", categoryDao.findAll());
        return "cheese/edit";
    }

    @RequestMapping(value = "edit/{cheeseId}", method = RequestMethod.POST)
    public String processEditForm(@ModelAttribute @Valid Cheese editedCheese,
                                  Errors errors, @PathVariable int cheeseId,
                                  @RequestParam int categoryId, Model model) {
        Cheese cheeseToUpdate = cheeseDao.findOne(cheeseId);

        if(errors.hasErrors()) {
            editedCheese.setName(cheeseToUpdate.getName());
            model.addAttribute("cheese", editedCheese);
            model.addAttribute("categories", categoryDao.findAll());
            return "cheese/edit";
        }
        cheeseToUpdate.setName(editedCheese.getName());
        cheeseToUpdate.setDescription(editedCheese.getDescription());
        cheeseToUpdate.setCategory(categoryDao.findOne(categoryId));
        cheeseToUpdate.setRating(editedCheese.getRating());
        cheeseDao.save(cheeseToUpdate);
        return "redirect:/cheese";
    }

}
