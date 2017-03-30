package examples.sockjs.components;

import examples.sockjs.components.sockJsComponent.handlers.CloseHandler;
import examples.sockjs.components.sockJsComponent.handlers.CookieNeededEchoHandler;
import examples.sockjs.components.sockJsComponent.handlers.DisabledWebsocketEchoHandler;
import examples.sockjs.components.sockJsComponent.handlers.EchoHandler;

import jswf.components.http.RouteHandlerComponent;
import jswf.framework.Environment;

public class SockJsComponent extends RouteHandlerComponent {

    public SockJsComponent() {
        // Base Urls
        this.addGet("echo", "/echo", EchoHandler.class);
        this.addGet("disabled_websocket_echo", "/disabled_websocket_echo", DisabledWebsocketEchoHandler.class);
        this.addGet("cookie_needed_echo", "/cookie_needed_echo", CookieNeededEchoHandler.class);
        this.addGet("close", "/close", CloseHandler.class);

        // Static Urls
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
