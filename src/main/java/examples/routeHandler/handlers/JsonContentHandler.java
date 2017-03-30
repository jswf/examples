package examples.routeHandler.handlers;

import jswf.components.generic.HttpRequest;
import jswf.components.generic.HttpResponse;
import jswf.components.generic.RequestHandlerInterface;
import jswf.framework.Environment;

import java.io.IOException;

public class JsonContentHandler implements RequestHandlerInterface {

    public void handle(Environment environment) {
        HttpResponse response = (HttpResponse) environment.getResponse();
        response.setContentType("examples/json");
        try {
            HttpRequest request = (HttpRequest) environment.getRequest();
            response.addContent(request.getBody());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
