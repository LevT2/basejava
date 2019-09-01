package ru.javawebinar.basejava.storage.junit5;

import org.junit.jupiter.api.DisplayName;
import ru.javawebinar.basejava.storage.impl.MapResumeStorage;

@DisplayName("MapResumeStorage implementation")
public class MapResumeStorageTest5 extends AbstractStorageTest5 {
    public MapResumeStorageTest5() {
        super(new MapResumeStorage());
    }
}
