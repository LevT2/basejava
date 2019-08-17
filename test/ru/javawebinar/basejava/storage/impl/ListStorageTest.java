package ru.javawebinar.basejava.storage.impl;

import org.junit.jupiter.api.DisplayName;
import ru.javawebinar.basejava.storage.AbstractStorageTest;

@DisplayName("ListStorage implementation")
class ListStorageTest extends AbstractStorageTest {
    public ListStorageTest() {
        super(new ListStorage());
    }
}