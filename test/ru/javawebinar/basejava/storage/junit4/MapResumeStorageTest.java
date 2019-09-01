package ru.javawebinar.basejava.storage.junit4;

import ru.javawebinar.basejava.storage.impl.MapResumeStorage;

public class MapResumeStorageTest extends AbstractStorageTest {

    public MapResumeStorageTest() {
        super(new MapResumeStorage());
    }
}