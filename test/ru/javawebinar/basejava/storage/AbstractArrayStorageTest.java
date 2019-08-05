package ru.javawebinar.basejava.storage;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static ru.javawebinar.basejava.storage.Util.*;


public abstract class AbstractArrayStorageTest {

    private Storage storage;

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

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

    @Nested
    class SaveOverflow {

        @BeforeEach
        void setUp() {
            storage.clear();
        }

        @Test
        @DisplayName("Should not throw within size limit, then throws when overflow")
        void fill() {
            // arrange
            String uuid = "New item";
            Executable throwingExecutable = () -> {
                storage.save(new Resume(uuid));
            };

            // act and assert
            assertDoesNotThrow(() -> {
                IntStream.iterate(0, n -> n + 1).
                        limit(AbstractArrayStorage.STORAGE_LIMIT).
                        mapToObj(i -> new Resume("Name" + i)).
                        forEach(storage::save);
            });

            RuntimeException thrown = assertThrows(
                    StorageException.class, throwingExecutable::execute, "Should throw generic StorageException"
            );

            assertEquals("Storage overflow", thrown.getMessage());
        }
    }

    @Test
    void size() throws Exception {
        assertEquals(3, storage.size());
    }

    @Test
    void clear() throws Exception {
        storage.clear();
        assertEquals(0, storage.size());
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
    void getAll() throws Exception {
        Resume[] test = new Resume[]{R1, R2, R3};
        assertEquals(3, storage.getAll().length);
        assertArrayEquals(test, storage.getAll());
    }

    @Test
    void save() throws Exception {
        Resume r4 = new Resume(UUID_4);
        storage.save(r4);

        assertEquals(4, storage.getAll().length);
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
    void get() throws Exception {
        assertSame(R1, storage.get(UUID_1));
    }

    @Test
    void getNonExisting() {
        assertThrows(
                NotExistStorageException.class,
                () -> storage.get("dummy")
        );
    }
}