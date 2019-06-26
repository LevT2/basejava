package ru.javawebinar.basejava.storage;

import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.impl.ArrayStorage;
import ru.javawebinar.basejava.storage.impl.SortedArrayStorage;

import java.util.Arrays;
import java.util.Enumeration;

import static org.junit.Assert.*;

public class AbstractArrayStorageTest {

    private Storage storage;

    private String uuid;

    @Before
    public void setUp() throws Exception {
        storage = new SortedArrayStorage();
        initialiseParameters();
    }

    @Test
    public void Crud(){
        String newUuid = create();
        getById(newUuid);
        getAll();
        update(newUuid);
        delete(newUuid);
    }


    private void initialiseParameters() {
        uuid = "uuid";
    }

    /// <summary>
    /// Creates this instance.
    /// </summary>
    /// <returns>The id of the new record.</returns>
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

    /// <summary>
    /// Updates the specified id.
    /// </summary>
    /// <param name="id">The id.</param>
    private void update (String uuid) {
        //Arrange
        Resume resume = storage.get(uuid);
        resume.setUuid("newuuid");

        //Act
        storage.update(resume);
        Resume updated = storage.get(uuid);

        //Assert
        assertEquals("newuuid", updated.getUuid(), "Record is not updated." );
    }

    /// <summary>
    /// Gets all.
    /// </summary>
    private void getAll(){
        //Act
       Resume[] items = storage.getAll();

        //Assert
        assertTrue("getAll returned no items.",items.length > 0);
        assertEquals(uuid, items[0].getUuid());
    }


    /// <summary>
    /// Gets the by ID.
    /// </summary>
    /// <param name="id">The id of the resume.</param>
    private void getById(String uuid) {
        //Act
        Resume resume = storage.get(uuid);

        //Assert
        assertNotNull("getByID returned null.", resume);
        assertEquals(uuid, resume.getUuid());
    }


    private void delete(String uuid) {
        //Act
        storage.delete(uuid);
        Resume resume = storage.get(uuid);

        //Assert
        assertNull("Record is not deleted.", resume);
    }


}