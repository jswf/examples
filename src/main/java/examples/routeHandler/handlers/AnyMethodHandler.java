package examples.routeHandler.handlers;

import jswf.components.generic.HttpRequest;
import jswf.components.generic.HttpResponse;
import jswf.components.generic.RequestHandlerInterface;
import jswf.framework.Environment;

public class AnyMethodHandler implements RequestHandlerInterface {

    public void handle(Environment environment) {
        HttpRequest request = (HttpRequest) environment.getRequest();
        HttpResponse response = (HttpResponse) environment.getResponse();
        try {
            response.addContent("method: " + request.getMethod());
        } catch (Exception e) {

        }
    }

}
