package ru.javawebinar.basejava.storage.junit5;

import org.junit.jupiter.api.DisplayName;
import ru.javawebinar.basejava.storage.impl.SortedArrayStorage;

@DisplayName("SortedArrayStorage implementation")
public class SortedArrayStorageTest5 extends AbstractArrayStorageTest5 {

    public SortedArrayStorageTest5() {
        super(new SortedArrayStorage());
    }
}