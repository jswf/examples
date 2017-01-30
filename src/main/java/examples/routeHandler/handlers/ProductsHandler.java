package examples.routeHandler.handlers;

import jswf.components.generic.RequestHandlerInterface;
import jswf.components.http.routeHandlerComponent.Request;
import jswf.components.http.routeHandlerComponent.Response;
import jswf.components.http.routeHandlerComponent.Route;
import jswf.framework.Environment;

import java.io.IOException;

public class ProductsHandler implements RequestHandlerInterface {

    public void handle(Environment environment) {
        Request request = (Request) environment.getRequest();
        Response response = (Response) environment.getResponse();
        Route route = (Route) request.getRoute();

        try {
            response.addContent("Group: " + route.getUriParameter("group") + ", By: " +  route.getUriParameter("uriParameter1") + " Name: " + route.getUriParameter("name"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
