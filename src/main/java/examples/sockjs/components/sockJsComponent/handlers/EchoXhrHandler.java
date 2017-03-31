package examples.sockjs.components.sockJsComponent.handlers;

import com.google.gson.Gson;
import examples.sockjs.components.SockJsComponent;
import examples.sockjs.components.sockJsComponent.sessionStorage.SessionStorageInterface;
import jswf.components.generic.HttpRequest;
import jswf.components.generic.HttpResponse;
import jswf.components.generic.HttpRoute;
import jswf.components.generic.RequestHandlerInterface;
import jswf.framework.Environment;
import jswf.framework.ServicesContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Timer;

public class EchoXhrHandler implements RequestHandlerInterface {

    public void handle(Environment environment) throws Exception {
        HttpRequest request = (HttpRequest) environment.getRequest();
        HttpResponse response = (HttpResponse) environment.getResponse();
        HttpRoute route = (HttpRoute) request.getRoute();

        response.setContentType("text/plain;charset=UTF-8");

        SockJsComponent sockJsComponent = (SockJsComponent) ServicesContainer.getService(SockJsComponent.class.getName());
        SessionStorageInterface storageInterface = sockJsComponent.getSessionStorage();

        String sessionId = route.getUriParameter("sessionId");
        if (!storageInterface.exists(sessionId)) {
            storageInterface.save(sessionId, "{}");
            response.addContent("o\n");
        } else {
            Object value = storageInterface.getValue(sessionId);
            Gson gson = new Gson();
            String json = gson.toJson(value);
            response.addContent("a" + json + "\n");
        }
    }

}
