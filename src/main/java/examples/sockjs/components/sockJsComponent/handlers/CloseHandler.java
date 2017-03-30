package examples.sockjs.components.sockJsComponent.handlers;

import jswf.components.generic.HttpRequest;
import jswf.components.generic.HttpResponse;
import jswf.components.generic.RequestHandlerInterface;
import jswf.framework.Environment;

public class CloseHandler implements RequestHandlerInterface {

    public void handle(Environment environment) throws Exception {
        HttpRequest request = (HttpRequest) environment.getRequest();
        HttpResponse response = (HttpResponse) environment.getResponse();
    }

}
