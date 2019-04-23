package org.launchcode.cheesemvc.models;

public class Cheese {

    private int cheeseId;
    private static int nextId = 1;
    private String name;
    private String description;

    public Cheese() {
        cheeseId = nextId++;
    }

    public Cheese(String aName, String aDescription) {
        this();
        name = aName;
        description = aDescription;
    }

    public int getCheeseId() {
        return cheeseId;
    }

    public void setCheeseId(int cheeseId) {
        this.cheeseId = cheeseId;
    }

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
}
