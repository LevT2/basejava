package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;

    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
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

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            if (size >= STORAGE_LIMIT) {
                System.out.println("Storage overflow at :" + size);
            } else {
                doSave(resume, index);
            }
        } else {
            System.out.println("Resume already exists with id :" + resume.getUuid());
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("No such Resume with id : " +  uuid);
            return null;
        } else {
            return storage[index];
        }
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            System.out.println("No such Resume with id : " + resume.getUuid());
        } else {
            storage[index] = resume;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("No such Resume with id : " +  uuid);
        } else {
            doDelete(index);
            size--;
        }
    }

    protected abstract void doSave(Resume resume, int index);

    protected abstract void doDelete(int index);

    protected abstract int getIndex(String uuid);
}