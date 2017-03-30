package examples.sockjs.components.sockJsComponent.handlers;

import jswf.components.generic.HttpRequest;
import jswf.components.generic.HttpResponse;
import jswf.components.generic.RequestHandlerInterface;
import jswf.framework.Environment;

import javax.servlet.http.Cookie;

public class CookieNeededEchoHandler implements RequestHandlerInterface {

    public void handle(Environment environment) throws Exception {
        HttpRequest request = (HttpRequest) environment.getRequest();
        HttpResponse response = (HttpResponse) environment.getResponse();

        Cookie cookie = new Cookie("JSESSIONID", "session123");
        response.addCookie(cookie);
        response.addContent(request.getBody());
    }

}
