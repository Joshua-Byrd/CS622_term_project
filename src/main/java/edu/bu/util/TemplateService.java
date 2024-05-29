package edu.bu.util;

import java.io.*;
import java.util.List;

public class TemplateService<T extends Serializable> {

    public void saveTemplates(String filePath, List<T> templates) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(templates);
        }
    }

    @SuppressWarnings("unchecked")
    public List<T> loadTemplates(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (List<T>) ois.readObject();
        }
    }
}
