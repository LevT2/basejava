package ru.javawebinar.basejava.storage.junit5;

import org.junit.jupiter.api.DisplayName;
import ru.javawebinar.basejava.storage.impl.ArrayStorage;

@DisplayName("ArrayStorage implementation")
public class ArrayStorageTest5 extends AbstractArrayStorageTest5 {

    public ArrayStorageTest5() {
        super(new ArrayStorage());
    }
}