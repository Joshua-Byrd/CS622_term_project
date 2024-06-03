package edu.bu.initializer;


import edu.bu.model.entitities.Monster;
import edu.bu.model.items.*;
import edu.bu.util.TemplateService;
import edu.bu.model.Room;
import edu.bu.model.FacadeModel;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * TemplateInitializer is responsible for creating and saving initial templates of monsters and rooms.
 * It uses the Facade pattern to interact with other components and ensures that the templates are saved for later use.
 */
public class TemplateInitializer {
    private TemplateService<Monster> monsterTemplateService = new TemplateService<>();
    private TemplateService<Room> roomTemplateService = new TemplateService<>();
    private FacadeItems facadeItems = FacadeItems.getTheInstance();
    private FacadeModel facadeModel = FacadeModel.getTheInstance();

    /**
     * INTENT: To create and save initial templates for monsters and rooms.
     * PRECONDITION: None.
     * POSTCONDITION: The templates for monsters and rooms are created and saved to their respective files.
     */
    public void createAndSaveTemplates() {
        // Define weapons and armors using FacadeItems
        Weapon claw = facadeItems.createWeapon("Claw", "Sharp claws", 2.0, 4, 0.0);
        Armor hide = facadeItems.createArmor("Hide", "Thick hide", 10.0, 3, 0.0);
        Weapon fang = facadeItems.createWeapon("Fang", "Sharp fangs", 1.5, 3, 0.0);
        Armor scales = facadeItems.createArmor("Scales", "Thick scales", 8.0, 1, 0.0);

        // Define some monster templates
        Monster goblin = new Monster("Goblin", "A sneaky goblin", 7, claw, hide);
        Monster snake = new Monster("Snake", "A venomous snake", 3, fang, scales);

        List<Monster> monsters = Arrays.asList(goblin, snake);

        // Create items using FacadeItems
        Weapon dagger = facadeItems.createWeapon("dagger",
                "a small dagger", 1.2, 4, 10.0);
        Weapon shortsword = facadeItems.createWeapon("shortsword","a small one-handed sword",
                3.0, 6, 20.0);
        Container<Item> smallChest = facadeItems.createContainer(
                "small chest",
                "a small chest made of dark wood",
                200.00,
                false,
                facadeItems.createInventory(100));
        smallChest.addItem(dagger);

        // Create an inventory for the starting room and add items to it
        Inventory<Item> startingRoomInventory = facadeItems.createInventory(1000);
        facadeItems.addItem(startingRoomInventory, smallChest);
        facadeItems.addItem(startingRoomInventory, shortsword);

        // Create the starting room using FacadeModel
        Room courtyard = facadeModel.createRoom("vast courtyard",
                "To the west you see the winding road that" +
                        " led you here. To the north and south, impenetrable forest stretches into the distance.\n" +
                        "To the east lies the entrance to the labyrinth known as the Desolate Depths.",
                startingRoomInventory);

        // Create another room with its own inventory using FacadeModel
        Inventory<Item> anotherRoomInventory = facadeItems.createInventory(1000);
        anotherRoomInventory.addItem(new Armor("iron armor", "a suit of iron armor", 10.0, 8, 50.0));
        anotherRoomInventory.addItem(new HealthPotion("health potion", "A swirly blue potion", 2, 12, 10));

        Room smallStoneRoom = facadeModel.createRoom("small stone room", "The walls are moss-covered and slick with " +
                "moisture. To the west is the door you came through. You can see no other openings.", anotherRoomInventory);

        courtyard.setConnection("east", smallStoneRoom);
        smallStoneRoom.setConnection("west", courtyard);

        List<Room> rooms = Arrays.asList(courtyard, smallStoneRoom);

        // Save templates
        try {
            monsterTemplateService.saveTemplates("monster_templates.dat", monsters);
            roomTemplateService.saveTemplates("room_templates.dat", rooms);
            System.out.println("Templates saved.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
