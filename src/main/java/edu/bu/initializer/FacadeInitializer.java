package edu.bu.initializer;

import edu.bu.initializer.GameInitializer;
import edu.bu.initializer.TemplateInitializer;
import edu.bu.model.entitities.Monster;
import edu.bu.model.Room;

import java.util.List;

/**
 * Facade for the initializer package, providing a singleton access point to the initializer functionalities.
 */
public class FacadeInitializer {

    private static FacadeInitializer instance;
    private final GameInitializer gameInitializer;
    private final TemplateInitializer templateInitializer;

    // Private constructor to prevent instantiation
    private FacadeInitializer() {
        this.gameInitializer = new GameInitializer();
        this.templateInitializer = new TemplateInitializer();
    }

    /**
     * INTENT: To provide a global point of access to the FacadeInitializer singleton instance.
     * PRECONDITION: None.
     * POSTCONDITION: Returns the singleton instance of FacadeInitializer.
     *
     * @return The singleton instance of FacadeInitializer.
     */
    public static synchronized FacadeInitializer getTheInstance() {
        if (instance == null) {
            instance = new FacadeInitializer();
        }
        return instance;
    }

    // GameInitializer Methods
    public List<Monster> loadMonsters() {
        return gameInitializer.loadMonsters();
    }

    public List<Room> loadRooms() {
        return gameInitializer.loadRooms();
    }

    public void initializeTemplatesIfNeeded() {
        gameInitializer.initializeTemplatesIfNeeded();
    }

    // TemplateInitializer Methods
    public void createAndSaveTemplates() {
        templateInitializer.createAndSaveTemplates();
    }
}
