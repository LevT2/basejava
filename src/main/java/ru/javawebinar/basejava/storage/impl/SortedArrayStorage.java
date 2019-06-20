package ru.javawebinar.basejava.storage.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.AbstractArrayStorage;

import java.util.Arrays;
import java.util.function.Consumer;

public class SortedArrayStorage extends AbstractArrayStorage {
    protected final Logger log = LoggerFactory.getLogger(SortedArrayStorage.class);


    @Override
    public void update(Resume resume) {
        int index = Arrays.binarySearch(storage, 0, size, resume);
        if (index < 0) {
            log.error("Resume {} does not exist", resume.getUuid());
        } else {
            insert(resume, index);
        }
    }

    @Override
    public void save(Resume resume) {
        if (size == STORAGE_LIMIT) {
            throw new IllegalStateException("Storage overflow.");
        }
        int index = Arrays.binarySearch(storage, 0, size, resume);
        if (index < 0) {
            // this is a new value to insert (not a duplicate).
            index = - index - 1;
        } else {
            log.error("Resume {} already exist", resume.getUuid());
        }
        insert(resume, index);
        size++;
    }

    private void insert(Resume resume, int index) {
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = resume;
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            log.error("Resume {} does not exist", uuid);
        } else {
            System.arraycopy(storage, index+1, storage, index, size - index);
            size--;
        }
    }



    @Override
    protected int getIndex(String uuid) {
        Resume resume = new Resume();
        resume.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, resume);
    }
}