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

public class EchoXhrSendHandler implements RequestHandlerInterface {

    public void handle(Environment environment) throws Exception {
        HttpRequest request = (HttpRequest) environment.getRequest();
        HttpResponse response = (HttpResponse) environment.getResponse();
        HttpRoute route = (HttpRoute) request.getRoute();

        response.setContentType("text/plain;charset=UTF-8");

        SockJsComponent sockJsComponent = (SockJsComponent) ServicesContainer.getService(SockJsComponent.class.getName());
        SessionStorageInterface storageInterface = sockJsComponent.getSessionStorage();
        String sessionId = route.getUriParameter("sessionId");

        if (!storageInterface.exists(sessionId)) {
            response.setStatus(404);
            return;
        }

        String content = request.getBody();
        Gson gson = new Gson();
        try {
            Object json = gson.fromJson(content, Object.class);
            storageInterface.save(sessionId, json);
            response.setStatus(204);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(500);
        }
    }

}
