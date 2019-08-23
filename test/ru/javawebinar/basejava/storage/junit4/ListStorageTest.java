package ru.javawebinar.basejava.storage.junit4;

import ru.javawebinar.basejava.storage.impl.ListStorage;

class ListStorageTest extends ru.javawebinar.basejava.storage.junit4.AbstractStorageTest  {
    public ListStorageTest() {
        super(new ListStorage());
    }
}