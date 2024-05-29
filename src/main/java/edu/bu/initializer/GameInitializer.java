package edu.bu.initializer;

import edu.bu.util.TemplateService;
import edu.bu.model.entitities.Monster;
import edu.bu.model.Room;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameInitializer {
    private TemplateService<Monster> monsterTemplateService = new TemplateService<>();
    private TemplateService<Room> roomTemplateService = new TemplateService<>();

    public List<Monster> loadMonsters() {
        try {
            return monsterTemplateService.loadTemplates("monster_templates.dat");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<Room> loadRooms() {
        try {
            return roomTemplateService.loadTemplates("room_templates.dat");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void initializeTemplatesIfNeeded() {
        File monsterFile = new File("monster_templates.dat");
        File roomFile = new File("room_templates.dat");

        if (!monsterFile.exists() || !roomFile.exists()) {
            TemplateInitializer initializer = new TemplateInitializer();
            initializer.createAndSaveTemplates();
        }
    }
}
