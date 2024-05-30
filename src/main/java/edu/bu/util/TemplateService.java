package edu.bu.util;

import java.io.*;
import java.util.List;

/**
 * Service class for saving and loading templates of objects that implement the Serializable interface.
 * This class provides methods to persist and retrieve lists of objects to and from files.
 *
 * @param <T> The type of objects to be saved and loaded, which must implement Serializable.
 */
public class TemplateService<T extends Serializable> {

    /**
     * INTENT: To save a list of templates to a specified file.
     * PRECONDITION 1: The filePath must be a valid file path
     * PRECONDITION 2: The templates list must not be null.
     * POSTCONDITION: The list of templates is serialized and saved to the specified file.
     *
     * @param aFilePath The path of the file where the templates will be saved.
     * @param someTemplates The list of templates to be saved.
     * @throws IOException If an I/O error occurs during the saving process.
     */
    public void saveTemplates(String aFilePath, List<T> someTemplates) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(aFilePath))) {
            oos.writeObject(someTemplates);
        }
    }

    /**
     * INTENT: To load a list of templates from a specified file.
     * PRECONDITION 1: The filePath must be a valid file path.
     * PRECONDITION 2: The file must exist.
     * POSTCONDITION: The list of templates is deserialized and returned from the specified file.
     *
     * @param aFilePath The path of the file from which the templates will be loaded.
     * @return The list of templates loaded from the specified file.
     * @throws IOException If an I/O error occurs during the loading process.
     * @throws ClassNotFoundException If the class of a serialized object cannot be found.
     */
    @SuppressWarnings("unchecked")
    public List<T> loadTemplates(String aFilePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(aFilePath))) {
            return (List<T>) ois.readObject();
        }
    }
}
