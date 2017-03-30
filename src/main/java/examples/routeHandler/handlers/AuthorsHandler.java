package examples.routeHandler.handlers;

import jswf.components.generic.HttpRequest;
import jswf.components.generic.HttpResponse;
import jswf.components.generic.HttpRoute;
import jswf.components.generic.RequestHandlerInterface;
import jswf.framework.Environment;

import java.io.IOException;

public class AuthorsHandler implements RequestHandlerInterface {

    public void handle(Environment environment) {
        HttpRequest request = (HttpRequest) environment.getRequest();
        HttpResponse response = (HttpResponse) environment.getResponse();
        HttpRoute route = (HttpRoute) request.getRoute();

        try {
            response.addContent("Author: " + route.getUriParameter("author"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
