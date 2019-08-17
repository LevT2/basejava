package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract void doSave(Object index, Resume r);

    protected abstract void doUpdate(Object index, Resume r);

    protected abstract Resume doGet(Object index);

    protected abstract void doDelete(Object index);

    protected abstract Object getIndex(String uuid);

    protected abstract boolean isPresent(Object index);

    @Override
    public void save(Resume r) {
        Object index = getNotExistingKey(r.getUuid());
        doSave(index, r);
    }

    @Override
    public void update(Resume r) {
        Object index = getExistingKey(r.getUuid());
        doUpdate(index, r);
    }

    @Override
    public void delete(String uuid) {
        Object index = getExistingKey(uuid);
        doDelete(index);
    }

    @Override
    public Resume get(String uuid) {
        Object index = getExistingKey(uuid);
        return doGet(index);
    }

    protected Object getExistingKey(String uuid) {
        Object index = getIndex(uuid);
        if (!isPresent(index)) {
            throw new NotExistStorageException(uuid);
        }
        return index;
    }

    protected Object getNotExistingKey(String uuid) {
        Object index = getIndex(uuid);
        if (isPresent(index)) {
            throw new ExistStorageException(uuid);
        }
        return index;
    }
}
