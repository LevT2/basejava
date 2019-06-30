package ru.javawebinar.basejava.storage.impl;

import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.AbstractArrayStorage;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());

        if (index < 0) {
            // this is a new value to doSave (not a duplicate).
            if (size == STORAGE_LIMIT) {
                log.error("Storage overflow at :" + size);
            } else {
                index = -index - 1;
                doSave(resume, index);
                size++;
            }
        } else {
            log.error("Resume {} already exist", resume.getUuid());
        }
    }

    @Override
    protected void doSave(Resume resume, int index) {
        storage[index] = resume;
    }

    @Override
    protected void doDelete(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - index);
    }

    @Override
    protected int getIndex(String uuid) {
        Resume resume = new Resume();
        resume.setUuid(uuid);
        if (size == 0) return -1;
        return Arrays.binarySearch(storage, 0, size, resume);
    }
}