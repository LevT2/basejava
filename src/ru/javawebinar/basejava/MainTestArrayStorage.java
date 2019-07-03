package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.Storage;
import ru.javawebinar.basejava.storage.impl.SortedArrayStorage;

/**
 * Test ru.javawebinar.basejava.storage.impl.ArrayStorage
 */

/**
 * Test for your ArrayStorage implementation
 */
public class MainTestArrayStorage {
    static final Storage ARRAY_STORAGE = new SortedArrayStorage();
    static int ARRAY_STORAGE_LIMIT = 3; //10_000;

    public static void main(String[] args) {
        Resume r1 = new Resume();
        r1.setUuid("uuid1");
        Resume r2 = new Resume();
        r2.setUuid("uuid2");
        Resume r3 = new Resume();
        r3.setUuid("uuid3");


        Resume r2new = new Resume();
        r2new.setUuid("uuid2");

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        ARRAY_STORAGE.save(r2new);

        ARRAY_STORAGE.update(r2new);
        System.out.println("Get r2new: " + ARRAY_STORAGE.get(r2new.getUuid()));
        printAll();

        ARRAY_STORAGE.delete("uuid3");

        ARRAY_STORAGE.get("uuid111");
        ARRAY_STORAGE.delete("uuid111");

        printAll();
        ARRAY_STORAGE.delete(r1.getUuid());
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());

//        try {
//            for (int i = 0; i <= ARRAY_STORAGE_LIMIT - 1; i++) {
//                Resume r = new Resume();
//                r.setUuid("uuid" + i);
//                ARRAY_STORAGE.save(r);
//            }
//        } catch (NullPointerException ex) {
//            System.out.println("<--NPE at size: " + ARRAY_STORAGE.size());
//        }
//
//        Resume r = new Resume();
//        try {
//            r.setUuid("uuid" + ARRAY_STORAGE_LIMIT);
//        } catch (IllegalStateException ex) {
//            System.out.println(ex.getMessage());
//        }

        // почему не печатает для ArrayStorage???
//        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}