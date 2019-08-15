package ru.javawebinar.basejava.storage.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.javawebinar.basejava.storage.AbstractStorageTest;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ListStorage implementation")
class ListStorageTest extends AbstractStorageTest{

    public ListStorageTest()
    {
        super(new ListStorage());
    }
}