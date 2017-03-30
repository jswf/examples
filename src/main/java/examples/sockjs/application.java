package examples.sockjs;

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
                .addPath("/src/main/java/examples/sockjs/public/html", "/{((.*)(.html)$)}")
                .addPath("/src/main/java/examples/sockjs/public", "/css/{(.*)*}")
                .addPath("/src/main/java/examples/sockjs/public", "/js/{(.*)*}")
                .addPath("/src/main/java/examples/sockjs/public", "/fonts/{(.*)*}")
        ;

        Framework framework = new Framework();
        framework
                .setRunner(runner)
                .addComponent(staticFilesServerComponent)
                .addComponent(new NoRouteMatchedComponent())
                .addComponent(new LogRequestComponent())
        ;

        try {
            framework.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
