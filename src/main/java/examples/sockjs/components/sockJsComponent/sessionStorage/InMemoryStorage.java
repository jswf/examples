package examples.sockjs.components.sockJsComponent.sessionStorage;

import java.util.HashMap;
import java.util.Map;

public class InMemoryStorage implements SessionStorageInterface {

    protected Map<String, Object> sessions;

    public InMemoryStorage() {
        sessions = new HashMap<>();
    }

    @Override
    public void save(String id, Object value) {
        sessions.put(id, value);
    }

    @Override
    public void delete(String id) {
        sessions.remove(id);
    }

    @Override
    public boolean exists(String id) {
        return sessions.containsKey(id);
    }

    @Override
    public Object getValue(String id) {
        return sessions.get(id);
    }
}
