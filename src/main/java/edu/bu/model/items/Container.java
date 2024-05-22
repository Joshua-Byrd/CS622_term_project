package edu.bu.model.items;

public class Container extends Item{
    private boolean isOpen;
    private Inventory<Item> items;

    public Container(String name, String description, Double weight, boolean isOpen, Inventory<Item> items) {
        super(name, description, weight);
        this.isOpen = isOpen;
        this.items = items;
    }

    //getter and setter methods
    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
