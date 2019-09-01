package ru.javawebinar.basejava.storage.junit5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.Storage;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static ru.javawebinar.basejava.storage.Util.STORAGE_LIMIT;


public abstract class AbstractArrayStorageTest5 extends AbstractStorageTest5 {

    protected AbstractArrayStorageTest5(Storage storage) {
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
                storage.save(new Resume(uuid, "Extra soldier"));
            };

            // act and assert
            assertDoesNotThrow(() -> IntStream.iterate(0, n -> n + 1).
                    limit(STORAGE_LIMIT).
                    mapToObj(i -> new Resume("Name" + i, "Name" + i)).
                    forEach(storage::save));

            RuntimeException thrown = assertThrows(
                    StorageException.class, throwingExecutable, "Should throw generic StorageException"
            );

            assertEquals("Storage overflow", thrown.getMessage());
        }
    }

}