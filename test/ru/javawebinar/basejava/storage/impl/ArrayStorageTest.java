package ru.javawebinar.basejava.storage.impl;

import org.junit.jupiter.api.DisplayName;
import ru.javawebinar.basejava.storage.AbstractArrayStorageTest;

@DisplayName("ArrayStorage implementation")
public class ArrayStorageTest extends AbstractArrayStorageTest {

    public ArrayStorageTest() {
        super(new ArrayStorage());
    }
}