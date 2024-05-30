package edu.bu.initializer;

import edu.bu.util.FacadeUtil;
import edu.bu.util.TemplateService;
import edu.bu.model.entitities.Monster;
import edu.bu.model.Room;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Initializes the list of Monsters and Rooms used by the game loop.  Also provides a method to
 * save the templates of Monsters and Rooms if they do not already exist.
 */
public class GameInitializer {
    private TemplateService<Monster> monsterTemplateService = new TemplateService<>();
    private TemplateService<Room> roomTemplateService = new TemplateService<>();

    /**
     * INTENT: To load a list of monster templates from a file.
     * PRECONDITION: The file specified by "monster_templates.dat" must exist and be readable.
     * POSTCONDITION: Return value == list of monsters if successful, an empty list otherwise.
     *
     * @return A list of Monster objects.
     */
    public List<Monster> loadMonsters() {
        try {
            return monsterTemplateService.loadTemplates("monster_templates.dat");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * INTENT: To load a list of room templates from a file.
     * PRECONDITION: The file specified by "room_templates.dat" must exist and be readable.
     * POSTCONDITION: Return value == list of Rooms if successful, an empty list otherwise
     *
     * @return A list of Room objects.
     */
    public List<Room> loadRooms() {
        try {
            return roomTemplateService.loadTemplates("room_templates.dat");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


    /**
     * INTENT: To initialize monster and room templates if they do not already exist.
     * PRECONDITION: None.
     * POSTCONDITION: If the template files do not exist, they are created by calling the TemplateInitializer.
     */
    public void initializeTemplatesIfNeeded() {
        File monsterFile = new File("monster_templates.dat");
        File roomFile = new File("room_templates.dat");

        if (!monsterFile.exists() || !roomFile.exists()) {
            TemplateInitializer initializer = new TemplateInitializer();
            initializer.createAndSaveTemplates();
        }
    }
}
