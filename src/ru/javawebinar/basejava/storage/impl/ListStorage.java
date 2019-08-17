package ru.javawebinar.basejava.storage.impl;

import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.AbstractStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ListStorage extends AbstractStorage {

    private List<Resume> list = new ArrayList<>();

    @Override
    protected void doSave(int index, Resume r) {
        list.add(r);
    }

    @Override
    protected void doUpdate(int index, Resume r) {
        list.set(index, r);
    }

    @Override
    protected Resume doGet(int index) {
        return list.get(index);
    }

    @Override
    protected void doDelete(int index) {
        list.remove(index);
    }

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < list.size(); i++) {
            if (uuid.equals(list.get(i).getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public Resume[] getAll() {
        return list.toArray(Resume[]::new);
    }

    @Override
    public int size() {
        return list.size();
    }
}
