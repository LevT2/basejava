package ru.javawebinar.basejava.storage.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.AbstractArrayStorage;


/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    protected final Logger log = LoggerFactory.getLogger(ArrayStorage.class);

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            if (size >= STORAGE_LIMIT) {
                log.error("Storage overflow at :" + size);
            } else {
                doSave(resume, size);
                size++;
            }
        } else {
            log.error("Resume {} already exists", resume.getUuid());
        }
    }

    @Override
    protected void doSave(Resume resume, int index) {
        storage[index] = resume;
    }

    @Override
    protected void doDelete(int index) {
        storage[index] = storage[size - 1];
        storage[size - 1] = null;
    }

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}