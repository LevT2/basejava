package ru.javawebinar.basejava.storage.junit4;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                ListStorageTest.class,
                MapUuidStorageTest.class,
                MapResumeStorageTest.class,
                ArrayStorageTest.class,
                SortedArrayStorageTest.class
        })
public class StorageTestSuite {

}
