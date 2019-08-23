package ru.javawebinar.basejava.storage.junit5;

import org.junit.jupiter.api.DisplayName;
import ru.javawebinar.basejava.storage.ArrayStorage;

@DisplayName("ArrayStorage implementation")
public class ArrayStorageTest extends AbstractArrayStorageTest {

    public ArrayStorageTest() {
        super(new ArrayStorage());
    }
}