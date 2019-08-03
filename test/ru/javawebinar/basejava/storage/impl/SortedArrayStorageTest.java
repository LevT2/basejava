package ru.javawebinar.basejava.storage.impl;

import ru.javawebinar.basejava.storage.AbstractArrayStorageTest;

public class SortedArrayStorageTest extends AbstractArrayStorageTest {

    public SortedArrayStorageTest() {
        super(new SortedArrayStorage());
    }
}