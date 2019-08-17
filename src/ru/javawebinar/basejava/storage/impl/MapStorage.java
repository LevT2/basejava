package ru.javawebinar.basejava.storage.impl;

import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.AbstractStorage;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    private Map<String, Resume> map = new HashMap<>();


    @Override
    protected void doSave(Object index, Resume r) {
        map.put((String)index,r);
    }

    @Override
    protected void doUpdate(Object index, Resume r) {
        map.put((String)index,r);
    }

    @Override
    protected Resume doGet(Object index) {
        return map.get(index);
    }

    @Override
    protected void doDelete(Object index) {
        map.remove(index);
    }

    @Override
    protected Object getIndex(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isPresent(Object index) {
        return map.containsKey(index);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Resume[] getAll() {
        return map.values().stream()
                .sorted(Resume::compareTo)
                .toArray(Resume[]::new);
    }

    @Override
    public int size() {
        return map.size();
    }
}
