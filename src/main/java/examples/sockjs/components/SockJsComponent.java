package examples.sockjs.components;

import examples.sockjs.components.sockJsComponent.handlers.*;

import examples.sockjs.components.sockJsComponent.sessionStorage.InMemoryStorage;
import examples.sockjs.components.sockJsComponent.sessionStorage.SessionStorageInterface;
import jswf.components.http.RouteHandlerComponent;
import jswf.framework.Environment;

public class SockJsComponent extends RouteHandlerComponent {

    private SessionStorageInterface sessionStorage;

    public SockJsComponent() {
        sessionStorage = new InMemoryStorage();

        // Base Urls
        this.addGet("echo", "/echo", EchoHandler.class);
        this.addPost("echoWithSession", "/echo/000/{sessionId:[A-Za-z0-9\\-\\_]+}/xhr", EchoXhrHandler.class);
        this.addPost("echoWithSessionSend", "/echo/000/{sessionId:[A-Za-z0-9\\-\\_]+}/xhr_send", EchoXhrSendHandler.class);
        this.addGet("disabled_websocket_echo", "/disabled_websocket_echo", DisabledWebsocketEchoHandler.class);
        this.addGet("cookie_needed_echo", "/cookie_needed_echo", CookieNeededEchoHandler.class);
        this.addGet("close", "/close", CloseHandler.class);

//        this.addPost("test", "/test", TestHandler.class);
    }

    public SessionStorageInterface getSessionStorage() {
        return sessionStorage;
    }

    public void setSessionStorage(SessionStorageInterface sessionStorage) {
        this.sessionStorage = sessionStorage;
    }

    @Override
    public void invoke(Environment environment) {
        super.invoke(environment);
    }

    @Override
    public String getServiceName() {
        return SockJsComponent.class.getName();
    }

}
