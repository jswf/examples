package examples.routeHandler.handlers;

import jswf.components.http.routeHandlerComponent.Request;
import jswf.components.generic.RequestHandlerInterface;
import jswf.components.http.routeHandlerComponent.Response;
import jswf.framework.Environment;

public class AnyMethodHandler implements RequestHandlerInterface {

    public void handle(Environment environment) {
        Request request = (Request) environment.getRequest();
        Response response = (Response) environment.getResponse();
        try {
            response.addContent("method: " + request.getMethod());
        } catch (Exception e) {

        }
    }

}
