package ru.javawebinar.basejava.storage.impl;

import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.AbstractStorage;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private List<Resume> list = new ArrayList<>();

    @Override
    protected void doSave(Object index, Resume r) {
        list.add(r);
    }

    @Override
    protected void doUpdate(Object index, Resume r) {
        list.set((int) index, r);
    }

    @Override
    protected Resume doGet(Object index) {
        return list.get((int) index);
    }

    @Override
    protected void doDelete(Object index) {
        list.remove(((int) index));
    }

    @Override
    protected Object getIndex(String uuid) {
        for (int i = 0; i < list.size(); i++) {
            if (uuid.equals(list.get(i).getUuid())) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean isPresent(Object index) {
        return index != null;
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
