package edu.bu.model.items;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A collection of T's extending Item, representing the contents of a container, room, or player's inventory
 * @param <T>
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Weapon.class, name = "weapon"),
        @JsonSubTypes.Type(value = Armor.class, name = "armor"),
        @JsonSubTypes.Type(value = Container.class, name = "container")
})
public class Inventory<T extends Item> {
    private List<T> items;
    private double maximumWeight;

    @JsonCreator
    public Inventory(@JsonProperty("maximumWeight") double maximumWeight) {
        this.items = new ArrayList<>();
        this.maximumWeight = maximumWeight;
    }

    /**
     * INTENT: Checks if a given item can be added to the inventory without exceeding the maximum weight.
     * PRECONDITION: anItem must not be null.
     * POSTCONDITION: return value = true if total weight + item weight <= maximum weight; false otherwise
     *
     * @param anItem The item to check.
     * @return true if the item can be added, false otherwise.
     */
    public boolean canAddItem(T anItem) {
        return getTotalWeight() + anItem.getWeight() <= maximumWeight;
    }

    /**
     * INTENT: Adds a given item to the inventory.
     * PRECONDITION: anItem must not be null and should be able to be added without exceeding the maximum weight.
     * POSTCONDITION: this.items += anItem
     *
     * @param anItem The item to add.
     */
    public void addItem(T anItem) {
        items.add(anItem);
    }

    /**
     * INTENT: Removes a given item from the inventory.
     * PRECONDITION: anItem must not be null and must exist in the inventory.
     * POSTCONDITION: this.items -= anItem
     *
     * @param anItem The item to remove.
     */
    public void removeItem(T anItem) {
        items.remove(anItem);
    }


    /**
     * INTENT: Checks if the inventory contains a given item.
     * PRECONDITION: anItem must not be null.
     * POSTCONDITION: Return value = true if this.item contains anItem; false otherwise
     *
     * @param anItem The item to check.
     * @return true if the item is in the inventory, false otherwise.
     */
    public boolean contains(T anItem) {
        return items.contains(anItem);
    }

    /**
     * INTENT: Clears all items from the inventory.
     * PRECONDITION: None.
     * POSTCONDITION: The inventory is empty.
     */
    public void clear() {
        items.clear();
    }

    /**
     * INTENT: Calculates the total weight of all items in the inventory.
     * PRECONDITION: None.
     * POSTCONDITION: Return value = the sum of the weights of items in this.items
     *
     * @return The total weight of all items in the inventory.
     */
    @JsonIgnore
    public double getTotalWeight() {
        return items.stream()
                .mapToDouble(Item::getWeight)
                .sum();
    }


    /**
     * INTENT: Finds an item in the inventory by its name.
     * PRECONDITION: name must not be null.
     * POSTCONDITION: Returns the item with the given name, or null if no such item exists.
     *
     * @param aName The name of the item to find.
     * @return The item with the given name, or null if no such item exists.
     */
    public T findItemByName(String aName) {
        return items.stream()
                .filter(item -> item.getName().equalsIgnoreCase(aName))
                .findFirst()
                .orElse(null);
    }

    public List<T> getTradeableItems() {
        return items.stream()
                .filter(item -> item instanceof Tradeable)
                .collect(Collectors.toList());
    }

    //getter and setter methods
    public double getMaximumWeight() {
        return maximumWeight;
    }

    public void setMaximumWeight(double aMaximumWeight) {
        this.maximumWeight = aMaximumWeight;
    }
    @JsonIgnore
    public int getSize() {
        return items.size();
    }

    @JsonIgnore
    public T getItem(int anIndex) {
        return items.get(anIndex);
    }
    @JsonIgnore
    public List<T> getAllItems() {
        return new ArrayList<>(items);
    }

    // Add getter for items to ensure it is serialized
    public List<T> getItems() {
        return items;
    }

    public void setItems (ArrayList<T> someItems) {
        this.items = someItems;
    }

}
