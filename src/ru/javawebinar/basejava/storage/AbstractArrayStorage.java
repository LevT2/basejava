package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    protected abstract void implDelete(int index);

    protected abstract void implSave(int index, Resume r);

    protected abstract Object getIndex(String uuid);

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public void doUpdate(Object index, Resume r) {
        storage[(Integer)index] = r;
    }

    @Override
    public Resume doGet(Object index) {
        return storage[(Integer)index];
    }

    @Override
    public void doDelete(Object index) {
        implDelete((Integer)index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    @Override
    public void doSave(Object index, Resume r) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            implSave((Integer)index, r);
            size++;
        }
    }

    @Override
    public boolean isPresent(Object index) {
        return (Integer) index >= 0;
    }
}