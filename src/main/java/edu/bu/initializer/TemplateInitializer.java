package edu.bu.initializer;


import edu.bu.model.entitities.Monster;
import edu.bu.util.TemplateService;
import edu.bu.model.entitities.Monster;
import edu.bu.model.Room;
import edu.bu.model.items.Armor;
import edu.bu.model.items.Container;
import edu.bu.model.items.Inventory;
import edu.bu.model.items.Item;
import edu.bu.model.items.Weapon;
import edu.bu.model.items.FacadeItems;
import edu.bu.model.FacadeModel;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class TemplateInitializer {
    private TemplateService<Monster> monsterTemplateService = new TemplateService<>();
    private TemplateService<Room> roomTemplateService = new TemplateService<>();
    private FacadeItems facadeItems = FacadeItems.getTheInstance();
    private FacadeModel facadeModel = FacadeModel.getTheInstance();

    public void createAndSaveTemplates() {
        // Define weapons and armors using FacadeItems
        Weapon claw = facadeItems.createWeapon("Claw", "Sharp claws", 2.0, 5, 0.0);
        Armor hide = facadeItems.createArmor("Hide", "Thick hide", 10.0, 3, 0.0);
        Weapon fang = facadeItems.createWeapon("Fang", "Sharp fangs", 1.5, 4, 0.0);
        Armor scales = facadeItems.createArmor("Scales", "Thick scales", 8.0, 4, 0.0);

        // Define some monster templates
        Monster goblin = new Monster("Goblin", "A sneaky goblin", 30, claw, hide);
        Monster snake = new Monster("Snake", "A venomous snake", 25, fang, scales);

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
//    public static void main(String[] args) {
//        TemplateInitializer initializer = new TemplateInitializer();
//        initializer.createAndSaveTemplates();
//    }
}
