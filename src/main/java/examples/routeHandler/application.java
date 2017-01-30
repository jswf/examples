package examples.routeHandler;

import examples.routeHandler.handlers.*;
import jswf.components.http.NoRouteMatchedComponent;
import jswf.framework.Framework;
import jswf.runners.JettyServer;
import jswf.components.http.LogRequestComponent;
import jswf.components.http.RouteHandlerComponent;

public class application {

    public static void main(String args[]) {
        RouteHandlerComponent routeHandler = new RouteHandlerComponent();
        routeHandler.addGet("index", "/", IndexHandler.class);
        routeHandler.addAny("any", "/methods", AnyMethodHandler.class);
        routeHandler.addPost("json-content", "/json", JsonContentHandler.class);
        routeHandler.addGet("authors", "/author/{author:(.*)+}", AuthorsHandler.class);
        routeHandler.addGet("booksByAuthor", "/authors/{author:(.*)+}/{category:(books|articles)}/{title:(.*)+}", BooksByAuthorHandler.class);
        routeHandler.addGet("products", "/products/{group:(.*)+}/{(byName)}/{name:(.*)+}", ProductsHandler.class);

        JettyServer runner = new JettyServer();

        Framework framework = new Framework();
        framework
                .setRunner(runner)
                .addComponent(new LogRequestComponent())
                .addComponent(routeHandler)
                .addComponent(new NoRouteMatchedComponent())
        ;

        try {
            framework.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
