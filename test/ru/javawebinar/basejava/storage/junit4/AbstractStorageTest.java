package ru.javawebinar.basejava.storage.junit4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.Storage;

import java.util.Arrays;

import static org.junit.Assert.*;
import static ru.javawebinar.basejava.storage.Util.*;

public class AbstractStorageTest {
    protected Storage storage;

//    public AbstractStorageTest(){}

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(R1);
        storage.save(R2);
        storage.save(R3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        storage.update(new Resume(UUID_2, "New Name"));
        assertEquals(R2, storage.get(R2.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNonExisting() throws Exception {
        storage.update(FAKE);
    }

    @Test
    public void getAllSorted() throws Exception {
        assertEquals(Arrays.asList(R1, R2, R3), storage.getAllSorted());
        assertEquals(3, storage.size());
    }

    @Test
    public void save() throws Exception {
        storage.save(R4);
        Assert.assertTrue(storage.getAllSorted().contains(R4));
        assertSize(4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExisting() throws Exception {
        storage.save(R1);
    }

    @Test
    public void delete() throws Exception {
        storage.delete(R2.getUuid());
        Assert.assertFalse(storage.getAllSorted().contains(R2));
        assertSize(2);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExisting() throws Exception {
        storage.delete(FAKE.getUuid());
    }

    @Test
    public void get() throws Exception {
        assertGet(R1);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNonExisting() throws Exception {
        storage.get(FAKE.getUuid());
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }

    private void assertGet(Resume r) {
        assertEquals(r, storage.get(r.getUuid()));
    }

}