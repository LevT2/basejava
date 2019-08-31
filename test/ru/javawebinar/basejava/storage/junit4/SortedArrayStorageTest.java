package ru.javawebinar.basejava.storage.junit4;

import ru.javawebinar.basejava.storage.impl.ArrayStorage;
import ru.javawebinar.basejava.storage.impl.SortedArrayStorage;

public class SortedArrayStorageTest extends AbstractArrayStorageTest {

    public SortedArrayStorageTest() {
        super(new SortedArrayStorage());
    }
}
