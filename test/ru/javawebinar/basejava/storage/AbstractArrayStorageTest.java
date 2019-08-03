package ru.javawebinar.basejava.storage;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("JUnit 5 Example")
public abstract class AbstractArrayStorageTest {
    protected Storage storage;

    protected static final String UUID_1 = "uuid1";
    protected static final String UUID_2 = "uuid2";
    protected static final String UUID_3 = "uuid3";

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    void beforeEach() {
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @AfterEach
    void afterEach() {
        storage.clear();
    }

    @Nested
    @DisplayName("When overflow")
    class WhenOverflow {

        @BeforeEach
        void setUp() {
            storage.clear();

            IntStream.iterate(0, n -> n + 1).
                    limit(AbstractArrayStorage.STORAGE_LIMIT).
                    mapToObj(i -> new Resume("Name" + i)).
                    forEach(storage::save);
        }


        @Test
        @DisplayName("Should not throw within size limit")
        void shouldNotThrow() {
            String uuid = "New item";
            if (storage.size() < AbstractArrayStorage.STORAGE_LIMIT) {
                storage.save(new Resume(uuid));
                assertDoesNotThrow(() -> {
                });
            }
        }

        @Test
        @DisplayName("Should throw correct exception if overflown")
        void shouldThrowCorrectException() {

            // arrange
            String uuid = "New item";
            Executable throwingExecutable = () -> {
                storage.save(new Resume(uuid));
            };

            // act and assert
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
        Resume r2new = new Resume(UUID_2);

        storage.update(r2new);
        assertEquals(UUID_2, storage.get(UUID_2).getUuid());
    }

    @Test
    public void getAll() throws Exception {

        Resume[] all = storage.getAll();

        assertEquals(UUID_1, all[0].getUuid());
        assertEquals(UUID_2, all[1].getUuid());
        assertEquals(UUID_3, all[2].getUuid());
    }

    @Test
    public void save() throws Exception {
        String newUuid = "uuid4";
        Resume r4 = new Resume(newUuid);

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
        Resume[] all = storage.getAll();
        Optional<Resume> first = Stream.of(all)
                .filter(r -> r.getUuid() == UUID_2)
                .findFirst();

        assertEquals(Optional.empty(), first);
    }

    @Test
    public void get() throws Exception {
        assertEquals(UUID_1, storage.get("uuid1").getUuid());
    }

    @Test
    public void getNonExisting() {
        String dummy = "dummy";

        final StorageException thrown = assertThrows(
                NotExistStorageException.class,
                () -> {
                    storage.get(dummy);
                }
        );
        assertEquals(dummy, thrown.getUuid());
    }
}