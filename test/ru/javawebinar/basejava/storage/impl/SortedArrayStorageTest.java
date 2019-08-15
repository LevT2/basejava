package ru.javawebinar.basejava.storage.impl;

import org.junit.jupiter.api.DisplayName;
import ru.javawebinar.basejava.storage.AbstractArrayStorageTest;

@DisplayName("SortedArrayStorage implementation")
public class SortedArrayStorageTest extends AbstractArrayStorageTest {

    public SortedArrayStorageTest() {
        super(new SortedArrayStorage());
    }
}