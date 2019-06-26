package ru.javawebinar.basejava.storage.impl;

import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.Storage;
import ru.javawebinar.basejava.storage.impl.ArrayStorage;
import ru.javawebinar.basejava.storage.impl.SortedArrayStorage;

import java.util.Arrays;
import java.util.Enumeration;

import static org.junit.Assert.*;

public class ArrayStorageTest {

    private Storage storage;

    private String uuid = "uuid";

    @Before
    public void setUp() throws Exception {
        storage = new ArrayStorage();
    }

    /**
     * Resume the crud.
     */
    @Test
    public void Crud(){
        String newUuid = create();
        getById(newUuid);
        getAll();
        update(newUuid);
        delete(newUuid);
    }

    /**
     * Creates this instance.
     * @return The id of the new record.
     */
    private String create() {
        //Arrange
        Resume resume = new Resume();
        resume.setUuid(uuid);

        //Act
        storage.save(resume);

        //Assert
        assertNotEquals("Creating new record does not return id", null, resume.getUuid());

        return resume.getUuid();
    }

    /**
     * Updates the specified id.
     * @param uuid The id.
     */
    private void update (String uuid) {
        //Arrange
        Resume newResume  = new Resume();
        newResume.setUuid(uuid);

        //Act
        storage.update(newResume);
        Resume updated = storage.get(uuid);

        //Assert
        assertEquals("Updated object does not equal", updated, newResume);
    }

    /**
     * Gets all.
     */
    private void getAll(){
        //Act
        Resume[] items = storage.getAll();

        //Assert
        assertTrue("getAll returned no items.",items.length > 0);
        assertEquals(uuid, items[0].getUuid());
    }

    /**
     * Gets the by ID.
     * @param uuid The id of the resume.
     */
    private void getById(String uuid) {
        //Act
        Resume resume = storage.get(uuid);

        //Assert
        assertNotNull("getByID returned null.", resume);
        assertEquals(uuid, resume.getUuid());
    }

    /**
     * Deletes the specified ID.
     * @param uuid The id.
     */
    private void delete(String uuid) {
        //Act
        storage.delete(uuid);
        Resume resume = storage.get(uuid);

        //Assert
        assertNull("Record is not deleted.", resume);
    }
}