package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

class Util {
    static final String UUID_1 = "uuid1";
    static final String UUID_2 = "uuid2";
    static final String UUID_3 = "uuid3";
    static final String UUID_4 = "uuid4";

    static final Resume R1;
    static final Resume R2;
    static final Resume R3;
    static final Resume R4;

    static {
        R1 = new Resume(UUID_1);
        R2 = new Resume(UUID_2);
        R3 = new Resume(UUID_3);
        R4 = new Resume(UUID_3);

//        R1 = new Resume(UUID_1, "Name1");
//        R2 = new Resume(UUID_2, "Name2");
//        R3 = new Resume(UUID_3, "Name3");
//        R4 = new Resume(UUID_3, "Name4");
    }
}
