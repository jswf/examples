package examples.fileUploader.handlers;

import jswf.components.generic.HttpRequest;
import jswf.components.generic.HttpResponse;
import jswf.components.generic.HttpRoute;
import jswf.framework.Environment;
import jswf.components.generic.RequestHandlerInterface;

import java.io.IOException;

public class UploadHandler implements RequestHandlerInterface {

    public void handle(Environment environment) {
        HttpResponse response = (HttpResponse) environment.getResponse();
        response.setContentType("examples/json");
        try {
            HttpRequest request = (HttpRequest) environment.getRequest();
            HttpRoute route = (HttpRoute) request.getRoute();
            response.addContent("content "+route.getUriParameter("uriParameter1"));
        } catch (IOException e) {

        }
    }

}
