package ru.javawebinar.basejava.storage.impl;

import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.AbstractArrayStorage;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume resume) {
        int index = getIndex(resume);
        if (index < 0) {
            // this is a new value to insertAt (not a duplicate).
            if (size == STORAGE_LIMIT) {
                throw new IllegalStateException("Storage overflow.");
            } else {
                size++;
                index = -index - 1;
                insertAt(resume, index);
//                size++;
            }
        } else {
            log.error("Resume {} already exist", resume.getUuid());
        }
    }

    @Override
    protected void insertAt(Resume resume, int index) {
        System.out.println("index: " + index);

        //Если закомментировать эту строку, то все CRUD тесты проходит, но в массив помещается не больше 16 элементов
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = resume;
    }

    @Override
    protected void fixAfterDelete(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - index);
    }

    @Override
    protected int getIndex(Resume resume) {
        return Arrays.binarySearch(storage, 0, size, resume);
    }

    @Override
    protected int getIndex(String uuid) {
        Resume resume = new Resume();
        resume.setUuid(uuid);
        return getIndex(resume);
    }
}