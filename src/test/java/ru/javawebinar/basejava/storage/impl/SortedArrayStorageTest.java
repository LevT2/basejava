

package ru.javawebinar.basejava.storage.impl;

import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.Storage;

import static org.junit.Assert.*;

public class SortedArrayStorageTest {

    private Storage storage;

    @Before
    public void setUp() throws Exception {
        storage = new SortedArrayStorage();
    }

    @Test
    public void emptySaveOneShouldHaveSizeOne(){
        Resume resume = new Resume("uuid1");
        storage.save(resume);
        assertEquals(1, storage.size());
    }

    @Test
    public void emptySaveTwoShouldHaveSizeOne(){
        Resume resume1 = new Resume("uuid2");
        Resume resume2 = new Resume("uuid1");
        storage.save(resume1);
        storage.save(resume2);
        assertEquals(2, storage.size());
    }

    @Test
    public void update() {
    }

    @Test
    public void save() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void getIndex() {
    }




//    @Test
//    public void testCRUD() throws Exception {
//
//        // Gets two random numbers
//        Long random = getRandom();
//        Long updateRandom = getRandom();
//
//        // Resume is the domain object
//        Resume resume = new Resume();
//
//        // The method findAll brings back all the objects from the DB
//        Resume[] firstFindAll = storage.getAll();
//
//        // Resume gets mock values and is persisted. Id is returned
//        resume = getMockResumeValues(resume, random);
//        storage.save(resume);
//        String uuid = resume.getUuid();
//
//        // Find the created object with the given Id and makes sure it has the right values
//        resume = storage.get(uuid);
//        assertNotNull("Object should exist", resume);
//        checkMockResumeValues(resume, random);
//
//        // Updates the object with new random values
//        resume = getMockResumeValues(resume, updateRandom);
//        storage.update(resume);
//
//        // Find the updated object and makes sure it has the new values
//        resume = storage.get(uuid);
//        assertNotNull("Object should exist", resume);
//        checkMockResumeValues(resume, updateRandom);
//
//        // Gets all the objects from the database...
//        Resume[] secondFindAll = storage.getAll();
//
//        // ...and makes sure there is one more object
//        if (firstFindAll.length + 1 != secondFindAll.length) fail("The collection size should have increased by 1");
//
//        // The object is now deleted
//        storage.delete(storage.get()resume);
//
//        // Find the object and make sure it has been removed
//        resume = em.find(Resume.class, id);
//        assertNull("Object should not exist", resume);
//
//        // Gets all the objects from the database...
//        int thirdFindAll = findAll();
//
//        // ...and makes sure we have the original size
//        if (firstFindAll != thirdFindAll) fail("The collection size should have be the same as original");
//    }
}