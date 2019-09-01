package ru.javawebinar.basejava.storage.junit5;

import org.junit.jupiter.api.DisplayName;
import ru.javawebinar.basejava.storage.impl.ListStorage;

@DisplayName("ListStorage implementation")
class ListStorageTest5 extends AbstractStorageTest5 {
    public ListStorageTest5() {
        super(new ListStorage());
    }
}