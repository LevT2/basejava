package ru.javawebinar.basejava.storage.junit4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.Storage;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static ru.javawebinar.basejava.storage.Util.*;

public abstract class AbstractStorageTest {
    protected Storage storage;

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
        Resume r2New = new Resume(UUID_2, "New Name");
        storage.update(r2New);
        assertEquals(r2New, storage.get(R2.getUuid()));
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
        assertGet(R4);
        assertSize(4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExisting() throws Exception {
        storage.save(R1);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete(R2.getUuid());
        assertSize(2);
        storage.get(R2.getUuid());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExisting() throws Exception {
        storage.delete(FAKE.getUuid());  // UUID_FAKE
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

    private void assertGet(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }

}