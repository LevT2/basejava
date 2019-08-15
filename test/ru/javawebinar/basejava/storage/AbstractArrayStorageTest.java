package ru.javawebinar.basejava.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;


public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    protected AbstractArrayStorageTest(Storage storage) {
        super(storage);
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
}