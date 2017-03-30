package examples.sockjs;

import examples.sockjs.components.SockJsComponent;
import jswf.components.http.LogRequestComponent;
import jswf.components.http.NoRouteMatchedComponent;
import jswf.components.http.StaticFilesServerComponent;
import jswf.framework.Framework;
import jswf.runners.JettyServer;

public class application {

    public static void main(String args[]) {
        JettyServer runner = new JettyServer();

        StaticFilesServerComponent staticFilesServerComponent = new StaticFilesServerComponent();
        staticFilesServerComponent
                .setBasePath(System.getProperty("user.dir"))
                .addPath("/src/main/java/examples/sockjs/public/html", "/{(.*)(.html)$}")
                .addPath("/src/main/java/examples/sockjs/public", "/css/{(.*)*}")
                .addPath("/src/main/java/examples/sockjs/public", "/js/{(.*)*}")
                .addPath("/src/main/java/examples/sockjs/public", "/fonts/{(.*)*}")
        ;

        SockJsComponent sockJsComponent = new SockJsComponent();

        Framework framework = new Framework();
        framework
                .setRunner(runner)
                .addComponent(new LogRequestComponent())
                .addComponent(staticFilesServerComponent)
                .addComponent(sockJsComponent)
                .addComponent(new NoRouteMatchedComponent())
        ;

        try {
            framework.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
