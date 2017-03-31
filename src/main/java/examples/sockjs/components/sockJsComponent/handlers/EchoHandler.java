package examples.sockjs.components.sockJsComponent.handlers;

import jswf.components.generic.HttpRequest;
import jswf.components.generic.HttpResponse;
import jswf.components.generic.RequestHandlerInterface;
import jswf.framework.Environment;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Timer;


public class EchoHandler implements RequestHandlerInterface {

    Timer timer = new Timer("ClientNotifier");

    public void handle(Environment environment) throws Exception {
        HttpRequest request = (HttpRequest) environment.getRequest();
        HttpResponse response = (HttpResponse) environment.getResponse();

        HttpServletRequest servletRequest = request.getHttpServletRequest();
        HttpServletResponse servletResponse = response.getHttpServletResponse();


//        AsyncContext aCtx = servletRequest.startAsync(servletRequest, servletResponse);
//        // Suspend request for 30 Secs
//        timer.schedule(new TimerTask(aCtx) {
//            public void run() {
//                try{
//                    //read unread alerts count
////                    int unreadAlertCount = alertManager.getUnreadAlerts(username);
//                    // write unread alerts count
//                    servletResponse.getWriter().write(String.valueOf(Math.random()));
//                } catch(Exception e){
//                    aCtx.complete();
//                }
//            }
//        }, 30000);

        response.setContentType("text/plain;charset=UTF-8");
        response.addContent("Welcome to SockJS!\n");
    }

}
