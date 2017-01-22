package examples.servingStaticFiles;

import jswf.components.http.LogRequestComponent;
import jswf.components.http.StaticFilesServerComponent;
import jswf.framework.Framework;
import jswf.runners.JettyServer;

/**
 * Application to demonstrate how to use the staticFilesServerComponent and LogRequestComponent.
 * A couple of exceptions should appear in the console due to the missing ghost.js file
 * and the not allowed .java extension of file code.java.
 */
public class application {

    public static void main(String args[]) {
        JettyServer runner = new JettyServer();

        StaticFilesServerComponent staticFilesServerComponent = new StaticFilesServerComponent();
        staticFilesServerComponent
                .setBasePath(System.getProperty("user.dir"))
                .addPath("/src/main/java/examples/servingStaticFiles/public", "/{(.*)*}")
        ;

        Framework framework = new Framework();
        framework
                .setRunner(runner)
                .addComponent(staticFilesServerComponent)
                .addComponent(new LogRequestComponent())
        ;

        try {
            framework.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
