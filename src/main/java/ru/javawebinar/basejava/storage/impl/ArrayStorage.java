package ru.javawebinar.basejava.storage.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.AbstractArrayStorage;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    protected final Logger log = LoggerFactory.getLogger(ArrayStorage.class);

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            log.error("Resume {} not exist", resume.getUuid());
        } else {
            insert(resume,size);
        }
    }

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            if (size >= STORAGE_LIMIT) {
                log.error("Storage overflow");
            } else {
                insert(resume,index);
                size++;
            }
        } else {
            log.error("Resume {} already exists", resume.getUuid());;
        }
    }

    private void insert(Resume resume, int index) {
        storage[index] = resume;
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            log.error("Resume {} does not exist", uuid);
        } else {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}