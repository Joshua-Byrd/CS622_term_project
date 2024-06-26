package edu.bu.model.entitities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.bu.model.items.*;
import edu.bu.model.Room;
import edu.bu.util.Die;
import edu.bu.util.FacadeUtil;
import edu.bu.util.MessageService;

/**
 * Represents the player character
 */
public class Player extends Entity implements Combatant {
    private int id;
    private int maxHealth;
    private int currentHealth;
    private Room currentRoom;
    private Weapon equippedWeapon;
    private Armor equippedArmor;
    private int attackRating;
    private int defenseRating;
    private double goldHeld;
    private int roomsVisited;
    private int monstersDefeated;
    private int actionsTaken;
    private final FacadeUtil facadeUtil = FacadeUtil.getTheInstance();

    // Session-specific tracking
    private double sessionGoldCollected;
    private int sessionMonstersDefeated;
    private int sessionActionsTaken;
    private int sessionRoomsVisited;

    // Default constructor required by Jackson
    public Player() {
        super("", "", new Inventory<>(50));
    }

    @JsonCreator
    public Player(@JsonProperty("id") int anId,
                  @JsonProperty("name") String aName,
                  @JsonProperty("description") String aDescription,
                  @JsonProperty("health") int aHealth,
                  @JsonProperty("currentRoom") Room aCurrentRoom,
                  @JsonProperty("equippedWeapon") Weapon aWeapon,
                  @JsonProperty("equippedArmor") Armor aArmor,
                  @JsonProperty("inventory") Inventory<Item> anInventory,
                  @JsonProperty("goldHeld") double someGold,
                  @JsonProperty("roomsVisited") int someRooms,
                  @JsonProperty("monstersDefeated") int someMonsters) {
        super(aName, aDescription, anInventory);
        this.id = anId;
        this.maxHealth = aHealth;
        this.currentHealth = aHealth;
        this.currentRoom = aCurrentRoom;
        this.equippedWeapon = aWeapon;
        this.equippedArmor = aArmor;
        this.attackRating = aWeapon.getAttackRating();
        this.defenseRating = aArmor.getDefenseRating();
        this.goldHeld = someGold;
        this.roomsVisited = someRooms;
        this.monstersDefeated = someMonsters;
        this.actionsTaken = 0;
    }

    /**
     * INTENT: To allow the player to engage in combat with a monster, potentially dealing damage based on a successful hit check.
     * PRECONDITION: The target must not be null and must be an instance of Monster.
     * POSTCONDITION 1: If attack is successful, target health -= damageTaken
     * POSTCONDITION 2: Feedback message is displayed to the screen
     *
     * @param aTarget The entity that is the target of the attack.
     */
    @Override
    public void attack(Entity aTarget) {
        if (aTarget instanceof Monster) {
            MessageService.sendMessage("You attack with your " + getEquippedWeapon().getName() + "!\n");

            Monster monster = (Monster) aTarget;

            if (isHit(monster)) {
                int damageTaken = new Die(attackRating).rollDie();
                monster.takeDamage(damageTaken);
                facadeUtil.sendMessage("You hit for " + damageTaken + " damage!\n");
            } else {
                facadeUtil.sendMessage("You miss!\n");
            }
        }
    }

    /**
     * INTENT: To simulate the effect of taking damage during combat, reducing the player's health accordingly.
     * PRECONDITION: The damage amount must be a non-negative integer.
     * POSTCONDITION: health -= aDamage
     *
     * @param aDamage The amount of damage to be applied to the player's health.
     */
    @Override
    public void takeDamage(int aDamage) {
        this.currentHealth -= aDamage;
    }

    /**
     * INTENT: To add an item to a player's inventory. First checks to see if the given item is a Tradeable
     * item (non-tradeable items include chests and other items that a player cannot carry), then adds it to the
     * player's inventory.
     * PRECONDITION: The given item must implement the Tradeable interface, and there must be room in the
     * inventory.
     * POSTCONDITION 1: The given item is added to the inventory
     * POSTCONDITION 2: An IllegalArgumentException is thrown.
     * @param anItem an item to be added.
     * @throws IllegalArgumentException if item is not Tradeable
     */
    public void addItemToInventory(Item anItem) throws IllegalArgumentException{
        if (anItem instanceof Tradeable && this.getInventory().canAddItem(anItem)) {
            this.getInventory().addItem(anItem);
        } else {
            throw new IllegalArgumentException("Only Tradeable items can be added to the player's inventory");
        }
    }

    /**
     * INTENT: To pick up an item from the specified room's inventory and add it to the player's inventory.
     * PRECONDITION: The item must be present in the room's inventory, and the player must have enough carrying capacity.
     * POSTCONDITION: The item is removed from the room's inventory and added to the player's inventory.
     *
     * @param itemName The name of the item to pick up.
     * @throws IllegalArgumentException if the item is not found in the room's inventory or if the player cannot carry it.
     */
    public void pickUpItem(String itemName) throws IllegalArgumentException {
        Item item = currentRoom.getItems().findItemByName(itemName);

        if (item == null) {
            throw new IllegalArgumentException("There is no ." + itemName + " here.");
        }

        if (!this.getInventory().canAddItem(item)) {
            throw new IllegalArgumentException("You cannot carry this item. It is too heavy.");
        }

        currentRoom.getItems().removeItem(item);
        this.getInventory().addItem(item);
    }

    /**
     * INTENT: To drop an item from the player's inventory into the current room's inventory.
     * PRECONDITION: The item must be present in the player's inventory.
     * POSTCONDITION: The item is removed from the player's inventory and added to the room's inventory.
     *
     * @param itemName The name of the item to drop.
     * @throws IllegalArgumentException if the item is not found in the player's inventory.
     */
    public void dropItem(String itemName) throws IllegalArgumentException {
        Item item = this.getInventory().findItemByName(itemName);

        if (item == null) {
            throw new IllegalArgumentException("You do not have a " + itemName + ".");
        }

        this.getInventory().removeItem(item);
        currentRoom.getItems().addItem(item);
    }

    /**
     * INTENT: To get an item from a container in the player's inventory or the current room.
     * PRECONDITION: The container must be present in the player's inventory or the current room.
     * POSTCONDITION: The item is removed from the container and added to the player's inventory.
     *
     * @param containerName The name of the container to get the item from.
     * @param itemName The name of the item to get from the container.
     * @throws IllegalArgumentException if the container or item is not found.
     */
    public void getItemFromContainer(String containerName, String itemName) throws IllegalArgumentException {
        Container<Item> container = findContainer(containerName);
        if (container == null) {
            throw new IllegalArgumentException("There is no " + containerName + " here.");
        }

        Item item = container.getItems().findItemByName(itemName);
        if (item == null) {
            throw new IllegalArgumentException("The " + containerName + " does not contain " + itemName + ".");
        }

        if (!this.getInventory().canAddItem(item)) {
            throw new IllegalArgumentException("Cannot carry this item. Exceeds carrying capacity.");
        }

        container.removeItem(item);
        this.getInventory().addItem(item);
    }

    // Helper method to find a container in the player's inventory or the current room
    private Container<Item> findContainer(String containerName) {
        // Check player's inventory
        Item item = this.getInventory().findItemByName(containerName);
        if (item instanceof Container) {
            return (Container<Item>) item;
        }

        // Check current room's inventory
        item = this.getCurrentRoom().getItems().findItemByName(containerName);
        if (item instanceof Container) {
            return (Container<Item>) item;
        }

        return null;
    }

    /**
     * INTENT: To consume the given item.
     * PRECONDITION 1: Item must exist in the player's inventory
     * PRECONDITION 2: Item must implement the Consumeable interface
     * POSTCONDITION: If item != null, the effects of the item are implemented; else, an error is display
     * @param itemName the item to be consumed
     */
    public void consumeItem(String itemName) {
        Item item = getInventory().findItemByName(itemName);
        if (item instanceof Consumable) {
            ((Consumable) item).consume(this);
            getInventory().removeItem(item); // Remove the item after use
        } else {
            System.out.println("You can't consume that.");
        }
    }

    /**
     * INTENT: To add the given amount of gold to the player
     * PRECONDITION: amount must not be null
     * POSTCONDITION: this.goldHeal += amount
     * @param amount the amount to be added
     */
    public void addGold(double amount) {
        if (amount > 0) {
            this.goldHeld += amount;
            this.sessionGoldCollected += amount;
        }
    }

    /**
     * INTENT: To remove the given amount of gold from the player
     * PRECONDITION: amount must not be null
     * POSTCONDITION: this.goldHeal -= amount
     * @param amount the amount to be removed
     */
    public void removeGold(double amount) {
        if (amount > 0 && this.goldHeld >= amount) {
            this.goldHeld -= amount;
            System.out.println("You spent " + amount + " gold.");
        }
    }

    /**
     * INTENT: To resent the session specific variables.  For use when loading a previously saved character.
     * PRECONDITION: None
     * POSTCONDITION: All session-specific variables == 0
     */
    public void resetSessionStats() {
        this.sessionGoldCollected = 0;
        this.sessionMonstersDefeated = 0;
        this.sessionActionsTaken = 0;
        this.sessionRoomsVisited = 0;
    }

    //Getter and Setter methods
    @Override
    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int aHealth) {
        this.maxHealth = aHealth;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room aNewRoom) {
        this.currentRoom = aNewRoom;
    }

    public int getAttackRating() {
        return attackRating;
    }

    public void setAttackRating(int anAttackRating) {
        this.attackRating = anAttackRating;
    }

    public int getDefenseRating() {
        return defenseRating;
    }

    public void setDefenseRating(int aDefenseRating) {
        this.defenseRating = aDefenseRating;
    }

    @JsonIgnore
    public double getCurrentWeight() {
        return this.getInventory().getTotalWeight();
    }

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public void setEquippedWeapon(Weapon anEquippedWeapon) {
        this.equippedWeapon = anEquippedWeapon;
    }

    public Armor getEquippedArmor() {
        return equippedArmor;
    }

    public void setEquippedArmor(Armor anEquippedArmor) {
        this.equippedArmor = anEquippedArmor;
    }

    public double getGoldHeld() {
        return goldHeld;
    }

    public void setGoldHeld(double goldHeld) {
        this.goldHeld = goldHeld;
    }

    public int getRoomsVisited() {
        return roomsVisited;
    }

    public void setRoomsVisited(int roomsVisited) {
        this.roomsVisited = roomsVisited;
    }

    public void incrementRoomsVisited() {
        this.roomsVisited++;
        this.sessionRoomsVisited++;
    }

    public int getMonstersDefeated() {
        return monstersDefeated;
    }

    public void setMonstersDefeated(int monstersDefeated) {
        this.monstersDefeated = monstersDefeated;
    }

    public void incrementMonstersDefeated() {
        this.monstersDefeated++;
        this.sessionMonstersDefeated++;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public int getActionsTaken() { return actionsTaken; }

    public void setActionsTaken(int actionsTaken) { this.actionsTaken = actionsTaken; }

    public void incrementActionsTaken() {
        this.actionsTaken++;
        this.sessionActionsTaken++;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public double getSessionGoldCollected() {
        return sessionGoldCollected;
    }

    public void setSessionGoldCollected(double sessionGoldCollected) {
        this.sessionGoldCollected = sessionGoldCollected;
    }

    public int getSessionMonstersDefeated() {
        return sessionMonstersDefeated;
    }

    public void setSessionMonstersDefeated(int sessionMonstersDefeated) {
        this.sessionMonstersDefeated = sessionMonstersDefeated;
    }

    public int getSessionActionsTaken() {
        return sessionActionsTaken;
    }

    public void setSessionActionsTaken(int sessionActionsTaken) {
        this.sessionActionsTaken = sessionActionsTaken;
    }

    public int getSessionRoomsVisited() {
        return sessionRoomsVisited;
    }

    public void setSessionRoomsVisited(int sessionRoomsVisited) {
        this.sessionRoomsVisited = sessionRoomsVisited;
    }

    @Override
    public Inventory<Item> getInventory() {
        return (Inventory<Item>) super.getInventory();
    }

    @Override
    public void setInventory(Inventory<Item> inventory) {
        super.setInventory(inventory);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Player: ").append(getName()).append("\n");
        sb.append("Description: ").append(getDescription()).append("\n");
        sb.append("Maximum Health: ").append(getMaxHealth()).append("\n");
        sb.append("Current Health: ").append(getCurrentHealth()).append("\n");
        sb.append("Equipped Weapon: ").append(equippedWeapon != null ? equippedWeapon.getName() : "None").append("\n");
        sb.append("Equipped Armor: ").append(equippedArmor != null ? equippedArmor.getName() : "None").append("\n");
        sb.append("Attack Rating: ").append(getAttackRating()).append("\n");
        sb.append("Defense Rating: ").append(getDefenseRating()).append("\n");
        sb.append("Gold Held: ").append(getGoldHeld()).append("\n");
        sb.append("Rooms Visited: ").append(getRoomsVisited()).append("\n");
        sb.append("Monsters Defeated: ").append(getMonstersDefeated()).append("\n");

        return sb.toString();
    }
}

