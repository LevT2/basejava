package ru.javawebinar.basejava.storage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.javawebinar.basejava.storage.Util.*;

public abstract class AbstractStorageTest {
    protected Storage storage;

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

//    protected abstract void assertSize(int size);


    @BeforeEach
    void beforeEach() {
        storage.save(R1);
        storage.save(R2);
        storage.save(R3);
    }

    @AfterEach
    void afterEach() {
        storage.clear();
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
        Resume r2New = new Resume(UUID_2);
        storage.update(r2New);

        assertSame(r2New, storage.get(UUID_2));
    }


    @Test
    void updateNonExisting() {
        assertThrows(
                NotExistStorageException.class,
                () -> storage.update(new Resume("dummy"))
        );
    }

    @Test
    void save() throws Exception {
        Resume r4 = new Resume(UUID_4);
        storage.save(r4);

        assertSize(4);
        assertSame(r4, storage.get(UUID_4));
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
                () -> storage.delete("dummy")
        );
    }


    @Test
    void getAll() throws Exception {
        Resume[] test = new Resume[]{R1, R2, R3};
        assertEquals(3, storage.getAll().length);
        assertArrayEquals(test, storage.getAll());
    }


    @Test
    void get() throws Exception {
        assertGet(R1);
    }

    @Test
    void getNonExisting() {
        assertThrows(
                NotExistStorageException.class,
                () -> storage.get("dummy")
        );
    }


    protected void assertSize(int size) {
        assertEquals(size, storage.size());
    }

    private void assertGet(Resume r) {
        assertEquals(r, storage.get(r.getUuid()));
    }
}
