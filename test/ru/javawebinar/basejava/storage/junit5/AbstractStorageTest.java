package ru.javawebinar.basejava.storage.junit5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.Storage;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static ru.javawebinar.basejava.storage.Util.*;

public abstract class AbstractStorageTest {
    protected Storage storage;

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    void beforeEach() {
        storage.clear();
        storage.save(R1);
        storage.save(R2);
        storage.save(R3);
    }

    @Test
    void size() throws Exception {
        assertSize(3);
    }

    @Test
    void clear() throws Exception {
        storage.clear();
        assertSize(0);
    }


    @Test
    void update() throws Exception {
        Resume r2New = new Resume(UUID_2, "New Name");
        storage.update(r2New);

        assertEquals(r2New, storage.get(R2.getUuid()));
//        assertSame(r2New, storage.get(UUID_2));
    }


    @Test
    void updateNonExisting() {
        assertThrows(
                NotExistStorageException.class,
                () -> storage.update(FAKE)
        );
    }

    @Test
    void save() throws Exception {
        storage.save(R4);

        assertSize(4);
        assertSame(R4, storage.get(UUID_4));
    }


    @Test
    void saveExisting() throws Exception {
        assertThrows(ExistStorageException.class,
                () -> storage.save(R1));
    }


    @Test
    void delete() throws Exception {
        storage.delete(UUID_2);
        assertAll("storage size is decreased, and access to deleted item correctly throws",
                () -> assertEquals(2, storage.size()),
                () -> assertThrows(NotExistStorageException.class,
                        () -> storage.get(UUID_2))
        );
    }

    @Test
    void deleteNonExisting() {
        assertThrows(
                NotExistStorageException.class,
                () -> storage.delete(FAKE.getUuid())
        );
    }


    @Test
    void getAllSorted() throws Exception {
        assertEquals(Arrays.asList(R1, R2, R3), storage.getAllSorted());
        assertSize(3);
    }

//    @Test
//    void getAll() throws Exception {
//        Resume[] test = new Resume[]{R1, R2, R3};
//        assertEquals(3, storage.getAll().length);
//        assertArrayEquals(test, storage.getAll());
//    }


    @Test
    void get() throws Exception {
        assertGet(R1);
    }

    @Test
    void getNonExistinging() {
        assertThrows(
                NotExistStorageException.class,
                () -> storage.get(FAKE.getUuid())
        );
    }


    protected void assertSize(int size) {
        assertEquals(size, storage.size());
    }

    private void assertGet(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }
}
