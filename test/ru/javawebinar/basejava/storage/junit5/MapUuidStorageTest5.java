package ru.javawebinar.basejava.storage.junit5;

import org.junit.jupiter.api.DisplayName;
import ru.javawebinar.basejava.storage.impl.MapUuidStorage;

@DisplayName("MapUuidStorage implementation")
public class MapUuidStorageTest5 extends AbstractStorageTest5 {
    public MapUuidStorageTest5() {
        super(new MapUuidStorage());
    }
}
