package ru.javawebinar.basejava.storage.impl;

import org.junit.jupiter.api.DisplayName;
import ru.javawebinar.basejava.storage.AbstractStorageTest;

@DisplayName("MapStorage implementation")
public class MapStorageTest extends AbstractStorageTest {
    public MapStorageTest() {
        super(new MapStorage());
    }
}
