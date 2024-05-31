package edu.bu.tests.util;

import edu.bu.util.TemplateService;
import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class TestSerializableObject implements Serializable {
    private String name;

    public TestSerializableObject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TestSerializableObject that = (TestSerializableObject) obj;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

public class TemplateServiceTest {

    private TemplateService<TestSerializableObject> templateService;
    private final String testFilePath = "test_templates.dat";

    @BeforeEach
    public void setUp() {
        templateService = new TemplateService<>();
    }

    @AfterEach
    public void tearDown() {
        try {
            Files.deleteIfExists(Paths.get(testFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSaveTemplates() {
        List<TestSerializableObject> templates = Arrays.asList(
                new TestSerializableObject("Template1"),
                new TestSerializableObject("Template2")
        );

        assertDoesNotThrow(() -> templateService.saveTemplates(testFilePath, templates));

        assertTrue(Files.exists(Paths.get(testFilePath)), "File should be created after saving templates.");
    }

    @Test
    public void testLoadTemplates() throws IOException, ClassNotFoundException {
        List<TestSerializableObject> originalTemplates = Arrays.asList(
                new TestSerializableObject("Template1"),
                new TestSerializableObject("Template2")
        );

        templateService.saveTemplates(testFilePath, originalTemplates);

        List<TestSerializableObject> loadedTemplates = templateService.loadTemplates(testFilePath);

        assertNotNull(loadedTemplates, "Loaded templates should not be null.");
        assertEquals(originalTemplates.size(), loadedTemplates.size(), "Loaded templates should match the original size.");
        assertTrue(loadedTemplates.containsAll(originalTemplates), "Loaded templates should match the original templates.");
    }

    @Test
    public void testLoadTemplatesFileNotFound() {
        Exception exception = assertThrows(IOException.class, () -> templateService.loadTemplates("non_existent_file.dat"));
        assertTrue(exception.getMessage().contains("non_existent_file.dat"), "Exception message should indicate the missing file.");
    }
}
