package ru.javawebinar.basejava.storage.impl;

import org.junit.jupiter.api.DisplayName;
import ru.javawebinar.basejava.storage.AbstractStorageTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("MapStorage implementation")
public class MapStorageTest extends AbstractStorageTest {
    public MapStorageTest()
    {
        super(new MapStorage());
    }

//    protected void assertSize(int size) {
//        assertEquals(size, storage.size());
//    }
}
