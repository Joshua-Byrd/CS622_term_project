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


        // Create Goblin------------------------------------------------------------------------------------------------
        Weapon claw = facadeItems.createWeapon("Claw", "Sharp claws", 2.0, 4, 0.0);
        Armor hide = facadeItems.createArmor("Hide", "Thick hide", 10.0, 3, 0.0);
        Monster goblin = new Monster("Goblin", "A sneaky goblin", 7, claw, hide);

        //Create snake--------------------------------------------------------------------------------------------------
        Weapon fang = facadeItems.createWeapon("Fang", "Sharp fangs", 1.5, 3, 0.0);
        Armor scales = facadeItems.createArmor("Scales", "Thick scales", 8.0, 1, 0.0);
        Monster snake = new Monster("Snake", "A venomous snake", 3, fang, scales);

        //Create Ogre---------------------------------------------------------------------------------------------------
        Weapon club = facadeItems.createWeapon("Club", "A heavy wooden club", 5.0, 6, 0.0);
        Armor fur = facadeItems.createArmor("Fur", "Thick fur", 12.0, 2, 0.0);
        Monster ogre = new Monster("Ogre", "A large and brutish ogre", 15, club, fur);

        //Create Harpy---------------------------------------------------------------------------------------------------
        Weapon talons = facadeItems.createWeapon("Talons", "Sharp talons", 2.5, 5, 0.0);
        Armor feathers = facadeItems.createArmor("Feathers", "Light but tough feathers", 4.0, 1, 0.0);
        Monster harpy = new Monster("Harpy", "A screeching harpy with sharp talons", 10, talons, feathers);

        //Create Giant Scorpion-----------------------------------------------------------------------------------------
        Weapon stinger = facadeItems.createWeapon("Stinger", "A deadly stinger", 1.0, 8, 0.0);
        Armor carapace = facadeItems.createArmor("Carapace", "Hard exoskeleton", 6.0, 4, 0.0);
        Monster scorpion = new Monster("Giant Scorpion", "A giant scorpion with a deadly stinger", 12, stinger, carapace);

        //Create Giant Spider-------------------------------------------------------------------------------------------
        Weapon fangs = facadeItems.createWeapon("Fangs", "Venomous fangs", 1.8, 6, 0.0);
        Armor silk = facadeItems.createArmor("Silk", "Thick spider silk", 3.0, 2, 0.0);
        Monster spider = new Monster("Giant Spider", "A giant spider lurking in the shadows", 8, fangs, silk);

        List<Monster> monsters = Arrays.asList(goblin, snake, ogre, harpy, scorpion, spider);

        // Create courtyard (starting room)-----------------------------------------------------------------------------
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

        Inventory<Item> startingRoomInventory = facadeItems.createInventory(1000);
        facadeItems.addItem(startingRoomInventory, smallChest);
        facadeItems.addItem(startingRoomInventory, shortsword);

        Room courtyard = facadeModel.createRoom("vast courtyard",
                "To the west you see the winding road that" +
                        " led you here. To the north and south, impenetrable forest stretches into the distance.\n" +
                        "To the east lies the entrance to the labyrinth known as the Desolate Depths.",
                startingRoomInventory,
                10);

        // Create small stone room -------------------------------------------------------------------------------------
        Inventory<Item> smallStoneRoomInventory = facadeItems.createInventory(1000);
        smallStoneRoomInventory.addItem(new Armor("iron armor",
                "a suit of iron armor", 10.0, 8, 50.0));
        smallStoneRoomInventory.addItem(new HealthPotion("health potion",
                "A swirly blue potion", 2, 12, 10));

        Room smallStoneRoom = facadeModel.createRoom("small stone room",
                "The walls are moss-covered and slick with " +
                "moisture. To the west is a stone archway, and to the north you see a large wooden door.",
                smallStoneRoomInventory, 25);

        //Create large cave room ---------------------------------------------------------------------------------------
        Inventory<Item> largeCaveRoomInventory = facadeItems.createInventory(1000);
        largeCaveRoomInventory.addItem(new Weapon("spear",
                "a long staff with a small diamond-shaped blade set on one end",
                5.0, 8, 15.0));
        Room largeCaveRoom = facadeModel.createRoom("large cave",
                "The entranceway opens up into an enormous cavern, dimly lit by oil sconces set into the walls" +
                        ". The ceiling towers above you, disappearing into the darkness. to the south is a large" +
                        "wooden door, and to the west is dark tunnel.", largeCaveRoomInventory, 3);

        // Create hidden grotto room -----------------------------------------------------------------------------------
        Inventory<Item> hiddenGrottoInventory = facadeItems.createInventory(1000);
        hiddenGrottoInventory.addItem(new Weapon("enchanted bow",
                "A bow imbued with magical properties", 3.0, 12, 50.0));
        hiddenGrottoInventory.addItem(new HealthPotion("large health potion",
                "A large bottle filled with a glowing red liquid", 3, 25, 20));

        Room hiddenGrotto = facadeModel.createRoom("carved grotto",
                "In this mysterious grotto, carvings of grotesque faces line the walls. It is illuminated by" +
                        " a glowing pool of water in the center of the cave. It feels otherworldly here.",
                hiddenGrottoInventory, 50);

        // Create dark tunnel room -------------------------------------------------------------------------------------
        Inventory<Item> darkTunnelInventory = facadeItems.createInventory(1000);
        darkTunnelInventory.addItem(new Armor("chainmail",
                "Sturdy chainmail armor", 8.0, 10, 40.0));
        darkTunnelInventory.addItem(new HealthPotion("medium health potion",
                "A medium bottle filled with a shimmering blue liquid", 2, 18, 15));

        Room darkTunnel = facadeModel.createRoom("dark tunnel",
                "The corridor curves ahead to the north, and to the east you see the opening to a large" +
                        "cave. The air is cold and you can hear the faint sound of dripping water.",
                darkTunnelInventory, 0);

        //Create connections between rooms------------------------------------------------------------------------------
        courtyard.setConnection("east", smallStoneRoom);
        smallStoneRoom.setConnection("west", courtyard);
        smallStoneRoom.setConnection("north", largeCaveRoom);
        largeCaveRoom.setConnection("south", smallStoneRoom);
        largeCaveRoom.setConnection("west", darkTunnel);
        darkTunnel.setConnection("east", largeCaveRoom);
        darkTunnel.setConnection("north", hiddenGrotto);
        hiddenGrotto.setConnection("south", darkTunnel);

        List<Room> rooms = Arrays.asList(courtyard, smallStoneRoom, largeCaveRoom, darkTunnel, hiddenGrotto);

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
