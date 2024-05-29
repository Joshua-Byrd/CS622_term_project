package edu.bu.model.items;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.io.Serial;
import java.io.Serializable;

/**
 * Represents a container item (e.g., chest, box) that can hold other items.
 */
@JsonTypeName("container")
public class Container <T extends Item> extends Item implements Serializable {
    private boolean isOpen;
    private Inventory<T> items;

    @JsonCreator
    public Container(
            @JsonProperty("name") String name,
            @JsonProperty("description") String description,
            @JsonProperty("weight") Double weight,
            @JsonProperty("isOpen") boolean isOpen,
            @JsonProperty("items") Inventory<T> items) {
        super(name, description, weight);
        this.isOpen = isOpen;
        this.items = items;
    }

    /**
     * INTENT: Opens the container.
     * PRECONDITION: The container must not be null.
     * POSTCONDITION: The container is marked as open.
     */
    public void open() {
        this.isOpen = true;
    }

    /**
     * INTENT: Adds an item to the container.
     * PRECONDITION: The item must not be null.
     * POSTCONDITION: The item is added to the container's inventory.
     *
     * @param item The item to be added.
     */
    public void addItem(T item) {
        items.addItem(item);
    }

    /**
     * INTENT: Removes and returns an item from the container (possibly for adding to a player's inventory)
     * PRECONDITION: The item must not be null and must exist in the container.
     * POSTCONDITION: The item is removed from the container's inventory && return value == item
     *
     * @param anItem The item to be removed.
     */
    public T removeItem(T anItem) {
        T tempItem = items.findItemByName(anItem.getName());
        items.removeItem(anItem);
        return tempItem;
    }

    // Getter and setter methods
    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public Inventory<T> getItems() {
        return items;
    }

    public void setItems(Inventory<T> someItems) {
        this.items = someItems;
    }
}
