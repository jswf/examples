package examples.routeHandler.handlers;

import jswf.components.generic.HttpResponse;
import jswf.components.generic.RequestHandlerInterface;
import jswf.framework.Environment;

import java.io.IOException;

public class IndexHandler implements RequestHandlerInterface {

    public void handle(Environment environment) {
        HttpResponse response = (HttpResponse) environment.getResponse();

        try {
            response.addContent("Hello World!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
