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

    @BeforeAll
    static void beforeAll() {
    }

    @BeforeEach
    void beforeEach() {
        storage.save(r1);
        storage.save(r2);
        storage.save(r3);
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
                    StorageException.class, throwingExecutable::execute, "Should throw non specialized StorageException"
            );

            assertEquals("Storage overflow", thrown.getMessage());
        }
    }

    @Test
    public void size() throws Exception {
        assertEquals(3, storage.size());
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void update() throws Exception {
        Resume r2new = makeResume(UUID_2);
        storage.update(r2new);

        assertSame(storage.get(UUID_2), r2new);
    }

    @Test
    public void updateNonExisting() {
        assertThrows(
                NotExistStorageException.class,
                () -> {
                    storage.update(makeResume("dummy"));
                }
        );
    }


    @Test
    public void getAll() throws Exception {
        Resume[] test = new Resume[]{r1, r2, r3};
        assertArrayEquals(test, storage.getAll());
    }

    @Test
    public void save() throws Exception {
        String newUuid = UUID_4;
        Resume r4 = makeResume(newUuid);

        storage.save(r4);
        assertEquals(newUuid, storage.get(newUuid).getUuid());
    }

    @Test
    public void saveExisting() throws Exception {
        assertThrows(ExistStorageException.class,
                () -> storage.save(new Resume(UUID_1)));
    }

    @Test
    public void delete() throws Exception {
        storage.delete(UUID_2);

        assertAll("storage size is decreased, and access to deleted item correctly throws",
                () -> assertEquals(2, storage.size()),
                () -> assertThrows(NotExistStorageException.class,
                        () -> storage.get(UUID_2))
        );
    }

    @Test
    public void deleteNonExisting() {
        assertThrows(
                NotExistStorageException.class,
                () -> {
                    storage.delete("dummy");
                }
        );
    }

    @Test
    public void get() throws Exception {
        assertSame(r1, storage.get(UUID_1));
    }

    @Test
    public void getNonExisting() {
        assertThrows(
                NotExistStorageException.class,
                () -> {
                    storage.get("dummy");
                }
        );
    }
}