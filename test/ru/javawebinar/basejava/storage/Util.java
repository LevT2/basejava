package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

class Util {
    static final String UUID_1 = "uuid1";
    static final String UUID_2 = "uuid2";
    static final String UUID_3 = "uuid3";
    static final String UUID_4 = "uuid4";

    static final Resume r1;
    static final Resume r2;
    static final Resume r3;

    static Resume makeResume(String uuid){
        return new Resume(uuid);
    }

    static {
        r1 = makeResume(UUID_1);
        r2 = makeResume(UUID_2);
        r3 = makeResume(UUID_3);
    }
}
