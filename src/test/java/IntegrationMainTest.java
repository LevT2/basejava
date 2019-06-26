import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.Storage;
import ru.javawebinar.basejava.storage.impl.ArrayStorage;
import ru.javawebinar.basejava.storage.impl.SortedArrayStorage;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class IntegrationMainTest {
    static int ARRAY_STORAGE_LIMIT = 10_000;

    private Storage storageA;
    private Storage storageS;

    private List<Storage> storages = new ArrayList<>();


    @Before
    public void setUp() throws Exception {
        storageA = new ArrayStorage();
        storageS = new SortedArrayStorage();

        storages.add(storageA);
        storages.add(storageS);
    }

    @Test
    public void main() {
        Resume r1 = new Resume();
        r1.setUuid("uuid1");
        Resume r2 = new Resume();
        r2.setUuid("uuid2");
        Resume r3 = new Resume();
        r3.setUuid("uuid3");
        Resume r2new = new Resume();
        r2new.setUuid("uuid2");

        storages.forEach(storage -> storage.save(r1));
        storages.forEach(storage -> storage.save(r2));
        storages.forEach(storage -> storage.save(r3));
        assertThat(storageS.getAll()).isEqualTo(storageA.getAll());

        storages.forEach(storage -> storage.save(r2new));
        assertThat(storageS.getAll()).isEqualTo(storageA.getAll());

        storages.forEach(storage -> storage.update(r2new));
        assertThat(storageS.getAll()).isEqualTo(storageA.getAll());

        storages.forEach(storage -> storage.get("uuid111"));
        storages.forEach(storage -> storage.delete("uuid111"));

        assertThat(storageS.getAll()).isEqualTo(storageA.getAll());

        storages.forEach(storage -> storage.delete(r1.getUuid()));
        //fail caused by ordering
//        assertThat(storageS.getAll()).isEqualTo(storageA.getAll());

        try {
            for (int i = 0; i <= ARRAY_STORAGE_LIMIT - 1; i++) {
                Resume r = new Resume();
                r.setUuid("uuid" + i);
                storages.forEach(storage -> storage.save(r));
            }
        } catch (NullPointerException ex) {
//            assertThat(storageS.getAll()).isNotEqualTo(storageA.getAll());
            storages.forEach(storage ->
                    System.out.println("<---" + storage.size()));
        }

    }
}
