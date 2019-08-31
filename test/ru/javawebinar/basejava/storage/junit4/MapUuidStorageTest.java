package ru.javawebinar.basejava.storage.junit4;

import ru.javawebinar.basejava.storage.impl.MapUuidStorage;

public class MapUuidStorageTest extends AbstractStorageTest {

    public MapUuidStorageTest() {
        super(new MapUuidStorage());
    }
}