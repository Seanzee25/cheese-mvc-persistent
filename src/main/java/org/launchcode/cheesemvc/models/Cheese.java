package org.launchcode.cheesemvc.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Cheese {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15, message="Name must be between 3 and 15 characters")
    private String name;

    @NotNull
    @Size(min=1, message="Description must not be empty")
    private String description;

    @ManyToOne
    private Category category;

    @NotNull
    @Min(1)
    @Max(5)
    private int rating;

    @ManyToMany(mappedBy = "cheeses")
    private List<Menu> menus;

    public Cheese() { }

    public Cheese(String aName, String aDescription) {
        name = aName;
        description = aDescription;
    }

    public int getId() { return id; }

    public String getName() {
        return name;
    }

    public void setName(String aName) {
        name = aName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String aDescription) {
        description = aDescription;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public List<Menu> getMenus() {
        return menus;
    }
}
