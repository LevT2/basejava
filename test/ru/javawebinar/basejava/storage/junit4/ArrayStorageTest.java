package ru.javawebinar.basejava.storage.junit4;

import ru.javawebinar.basejava.storage.impl.ArrayStorage;

public class ArrayStorageTest extends AbstractArrayStorageTest {

    public ArrayStorageTest() {
        super(new ArrayStorage());
    }
}
