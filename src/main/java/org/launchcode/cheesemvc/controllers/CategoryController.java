package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.Category;
import org.launchcode.cheesemvc.models.Cheese;
import org.launchcode.cheesemvc.models.data.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "cheese/category")
public class CategoryController {

    @Autowired
    private CategoryDao categoryDao;

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("title", "Categories");
        model.addAttribute("categories", categoryDao.findAll());
        return "category/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addCategoryForm(Model model) {
        model.addAttribute("title", "Add New Category");
        model.addAttribute(new Category());
        return "category/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String addCategory(@ModelAttribute @Valid Category newCategory,
                              Errors errors, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("title", "Add New Category");
            return "category/add";
        }
        categoryDao.save(newCategory);
        return "redirect:";
    }

    @RequestMapping(value = "{categoryId}")
    public String listCheeseByCategory(Model model, @PathVariable int categoryId) {
        Category category = categoryDao.findOne(categoryId);
        List<Cheese> cheeses = category.getCheeses();
        model.addAttribute("title", "Cheeses in Category: " + category.getName());
        model.addAttribute("cheeses", cheeses);
        return "cheese/index";
    }

}
