package edu.bu.model.items;

import java.util.List;

/**
 * Facade for the items package, providing a singleton access point to the item functionalities.
 */
public class FacadeItems {

    private static FacadeItems instance;

    // Private constructor to prevent instantiation
    private FacadeItems() {
    }

    /**
     * INTENT: To provide a global point of access to the FacadeItems singleton instance.
     * PRECONDITION: None.
     * POSTCONDITION: Returns the singleton instance of FacadeItems.
     *
     * @return The singleton instance of FacadeItems.
     */
    public static synchronized FacadeItems getTheInstance() {
        if (instance == null) {
            instance = new FacadeItems();
        }
        return instance;
    }

    // Armor Methods
    public Armor createArmor(String name, String description, double weight, int defenseRating, double price) {
        return new Armor(name, description, weight, defenseRating, price);
    }

    public int getArmorDefenseRating(Armor armor) {
        return armor.getDefenseRating();
    }

    public void setArmorDefenseRating(Armor armor, int defenseRating) {
        armor.setDefenseRating(defenseRating);
    }

    // Weapon Methods
    public Weapon createWeapon(String name, String description, double weight, int attackRating, double price) {
        return new Weapon(name, description, weight, attackRating, price);
    }

    public int getWeaponAttackRating(Weapon weapon) {
        return weapon.getAttackRating();
    }

    public void setWeaponAttackRating(Weapon weapon, int attackRating) {
        weapon.setAttackRating(attackRating);
    }

    // Inventory Methods
    public <T extends Item> Inventory<T> createInventory(double maximumWeight) {
        return new Inventory<>(maximumWeight);
    }

    public <T extends Item> boolean canAddItem(Inventory<T> inventory, T item) {
        return inventory.canAddItem(item);
    }

    public <T extends Item> void addItem(Inventory<T> inventory, T item) {
        inventory.addItem(item);
    }

    public <T extends Item> void removeItem(Inventory<T> inventory, T item) {
        inventory.removeItem(item);
    }

    public <T extends Item> T getItem(Inventory<T> inventory, int index) {
        return inventory.getItem(index);
    }

    public <T extends Item> List<T> getAllItems(Inventory<T> inventory) {
        return inventory.getAllItems();
    }

    public <T extends Item> boolean contains(Inventory<T> inventory, T item) {
        return inventory.contains(item);
    }

    public <T extends Item> void clear(Inventory<T> inventory) {
        inventory.clear();
    }

    public <T extends Item> double getTotalWeight(Inventory<T> inventory) {
        return inventory.getTotalWeight();
    }

    public <T extends Item> T findItemByName(Inventory<T> inventory, String name) {
        return inventory.findItemByName(name);
    }

    public <T extends Item> double getMaximumWeight(Inventory<T> inventory) {
        return inventory.getMaximumWeight();
    }

    public <T extends Item> void setMaximumWeight(Inventory<T> inventory, double maximumWeight) {
        inventory.setMaximumWeight(maximumWeight);
    }

    // Container Methods
    public Container createContainer(String name, String description, double weight, boolean isOpen, Inventory<Item> items) {
        return new Container(name, description, weight, isOpen, items);
    }

    public boolean isContainerOpen(Container container) {
        return container.isOpen();
    }

    public void setContainerOpen(Container container, boolean isOpen) {
        container.setOpen(isOpen);
    }

    public Inventory<Item> getContainerItems(Container container) {
        return container.getItems();
    }

    public void setContainerItems(Container container, Inventory<Item> items) {
        container.setItems(items);
    }

}
