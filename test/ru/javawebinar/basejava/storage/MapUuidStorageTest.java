package ru.javawebinar.basejava.storage;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.AbstractStorageTest;
import ru.javawebinar.basejava.storage.impl.MapUuidStorage;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.javawebinar.basejava.storage.Util.*;

@DisplayName("MapStorage implementation")
public class MapUuidStorageTest extends AbstractStorageTest {
    public MapUuidStorageTest() {
        super(new MapUuidStorage());
    }

//    @Test
//    void getAll() throws Exception {
//        Resume[] test = new Resume[]{R1, R2, R3};
//        Resume[] all = storage.getAll();
//        Arrays.sort(all);
//        assertEquals(3, all.length);
//        assertArrayEquals(test, all);
//    }
}
