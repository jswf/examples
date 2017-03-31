package examples.sockjs.components.sockJsComponent.sessionStorage;

public interface SessionStorageInterface {

    void save(String id, Object value);
    void delete(String id);
    boolean exists(String id);
    Object getValue(String id);

}
