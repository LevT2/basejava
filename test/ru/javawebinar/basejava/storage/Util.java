package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

public class Util {
    public static final String UUID_1 = "uuid1";
    public static final String UUID_2 = "uuid2";
    public static final String UUID_3 = "uuid3";
    public static final String UUID_4 = "uuid4";
    public static final String UUID_FAKE = "dummy";

    public static final Resume R1;
    public static final Resume R2;
    public static final Resume R3;
    public static final Resume R4;
    public static final Resume FAKE;

    static {
//        R1 = new Resume(UUID_1);
//        R2 = new Resume(UUID_2);
//        R3 = new Resume(UUID_3);
//        R4 = new Resume(UUID_3);
//        FAKE = new Resume(UUID_FAKE);

        R1 = new Resume(UUID_1, "Name1");
        R2 = new Resume(UUID_2, "Name2");
        R3 = new Resume(UUID_3, "Name3");
        R4 = new Resume(UUID_4, "Name4");
        FAKE = new Resume(UUID_FAKE, "DUMMY");


//        R1 = new Resume(UUID_1, 1);
//        R2 = new Resume(UUID_2, 2);
//        R3 = new Resume(UUID_3, 3);
//        R4 = new Resume(UUID_3, 4);
//        FAKE = new Resume(UUID_FAKE, 111111);

    }
    public static final int STORAGE_LIMIT = 10000;
}
