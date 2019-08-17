package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.List;

public abstract class AbstractStorage implements Storage {

    protected abstract void doSave(int index, Resume r);

    protected abstract void doUpdate(int index, Resume r);

    protected abstract Resume doGet(int index);

    protected abstract void doDelete(int index);

    protected abstract int getIndex(String uuid);

    @Override
    public void save(Resume r) {
        int index = getNotExistingKey(r.getUuid());
        doSave(index, r);
    }

    @Override
    public void update(Resume r) {
        int index = getExistingKey(r.getUuid());
        doUpdate(index, r);
    }

    @Override
    public void delete(String uuid) {
        int index = getExistingKey(uuid);
        doDelete(index);
    }

    @Override
    public Resume get(String uuid) {
        int index = getExistingKey(uuid);
        return doGet(index);
    }

    protected int getExistingKey(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return index;
    }

    protected int getNotExistingKey(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            throw new ExistStorageException(uuid);
        }
        return index;
    }
}
