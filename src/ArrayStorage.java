import java.util.Arrays;
import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];

    private int size;

    void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    void update(Resume r) {
        int index = getIndex(r.uuid);

        if (index >= 0) {
            System.out.println("ERROR in method 'update' :" + r.uuid);
        } else {
            storage[index] = r;
        }
    }

    void save(Resume r) {
        int index = getIndex(r.uuid);

        if (index == 0) {
            System.out.println("ERROR in method 'save' :" + r.uuid);
        } else {
            if (size < 10000) {
                storage[size] = r;
                size++;
            } else {
                System.out.println("ERROR in method 'save' :" + r.uuid + " (storage backing array boundary has reached)");
            }
        }
    }

    Resume get(String uuid) {
        int index = getIndex(uuid);

        if (index >= 0) {
            return storage[index];
        } else {
            return null;
        }
    }


    void delete(String uuid) {
        int index = getIndex(uuid);

        if (index >= 0) {
//            System.arraycopy(storage, index + 1, storage, index, size - index - 1);
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }


    private int getIndex(String uuid) {
        for (int i = 0; i < size; ++i) {
            if (Objects.equals(storage[i].uuid, uuid)) {
                return i;
            }
        }
        return -1;
    }


    /**
     * @return array, contains only Resumes in storage (without null)
     */

    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    int size() {
        return size;
    }
}
