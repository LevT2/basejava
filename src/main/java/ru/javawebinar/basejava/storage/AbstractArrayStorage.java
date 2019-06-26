package ru.javawebinar.basejava.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    public static final int STORAGE_LIMIT = 10_000;
    protected final Logger log = LoggerFactory.getLogger(AbstractArrayStorage.class);

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            return null;
        }
        return storage[index];
    }

    public void update(Resume resume) {
        int index = getIndex(resume);
        if (index < 0) {
            log.error("Resume {} does not exist", resume.getUuid());
        } else {
            insertAt(resume, index);
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            log.error("Resume {} does not exist", uuid);
        } else {
            fixAfterDelete(index);
            size--;
        }
    }

    protected abstract void insertAt(Resume resume, int index);

    protected abstract void fixAfterDelete(int index);

    protected abstract int getIndex(Resume resume);

    protected abstract int getIndex(String uuid);
}