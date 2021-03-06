package examples.fileUploader;

import jswf.framework.Framework;
import jswf.components.http.DummyExceptionRendererComponent;
import jswf.components.http.RouteHandlerComponent;
import jswf.components.http.LogRequestComponent;
import jswf.components.http.StaticFilesServerComponent;
import jswf.runners.JettyServer;

import examples.fileUploader.handlers.UploadHandler;

public class application {

    public static void main(String args[]) {
        RouteHandlerComponent routeHandler = new RouteHandlerComponent();
        routeHandler.addGet("upload", "/upload", UploadHandler.class);

        JettyServer runner = new JettyServer();
        runner.setPort(8080);

        StaticFilesServerComponent staticFilesServerComponent = new StaticFilesServerComponent();
        staticFilesServerComponent
                .setBasePath(System.getProperty("user.dir"))
                .addPath("/src/main/java/examples/fileUploader/public", "/{(.*)*}")
        ;

        Framework framework = new Framework();
        framework
                .setRunner(runner)
                .addComponent(new LogRequestComponent())
                .addComponent(staticFilesServerComponent)
                .addComponent(routeHandler)
                .addComponent(new DummyExceptionRendererComponent())
        ;

        try {
            framework.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
