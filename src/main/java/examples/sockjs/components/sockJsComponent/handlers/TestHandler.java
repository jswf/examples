package examples.sockjs.components.sockJsComponent.handlers;

import jswf.components.generic.HttpRequest;
import jswf.components.generic.HttpResponse;
import jswf.components.generic.RequestHandlerInterface;
import jswf.framework.Environment;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class TestHandler implements RequestHandlerInterface {

    Timer timer = new Timer("ClientNotifier");

    final static String RESULTS_ATTR = "org.eclipse.jetty.demo.client";
    final static String DURATION_ATTR = "org.eclipse.jetty.demo.duration";
    final static String START_ATTR = "org.eclispe.jetty.demo.start";
    final static String ITEMS_PARAM = "items";
    final static String APPID_PARAM = "appid";


    public void handle(Environment environment) throws Exception {
        HttpRequest request = (HttpRequest) environment.getRequest();
        HttpResponse response = (HttpResponse) environment.getResponse();

        HttpServletRequest servletRequest = request.getHttpServletRequest();
        HttpServletResponse servletResponse = response.getHttpServletResponse();


        Long start=System.nanoTime();

        // Do we have results yet?
        Queue<Map<String, String>> results = (Queue<Map<String, String>>) request.getAttribute(RESULTS_ATTR);

        // If no results, this must be the first dispatch, so send the REST request(s)
        if (results == null)
        {
            // define results data structures
            final Queue<Map<String, String>> resultsQueue = new ConcurrentLinkedQueue<>();
            request.setAttribute(RESULTS_ATTR, results=resultsQueue);

            // suspend the request
            // This is done before scheduling async handling to avoid race of
            // dispatch before startAsync!
            final AsyncContext async = request.startAsync();
            async.setTimeout(30000);

            // extract keywords to search for
//            String[] keywords = request.getParameter(ITEMS_PARAM).split(",");
//            final AtomicInteger outstanding=new AtomicInteger(keywords.length);

            // save timing info and return
            request.setAttribute(START_ATTR, start);
            request.setAttribute(DURATION_ATTR, System.nanoTime() - start);

            return;
        }

        // We have results!

        // Generate the response

        response.setContentType("text/html");
        PrintWriter out = servletResponse.getWriter();
        out.println("<html><head>");
        out.println("</head><body><small>");

        long initial = (Long) request.getAttribute(DURATION_ATTR);
        long start0 = (Long) request.getAttribute(START_ATTR);

        long now = System.nanoTime();
        long total=now-start0;
        long generate=now-start;
        long thread=initial+generate;

        response.addContent("<b>Asynchronous: "+request.getParameter(ITEMS_PARAM)+"</b><br/>" +
                "Total Time: "+total+"ms<br/>" +
                "Thread held (<span class='red'>red</span>): "+thread+"ms (" + initial + " initial + " + generate + " generate )<br/>" +
                "Async wait (<span class='green'>green</span>): "+(total-thread)+"ms<br/><hr /></small></body></html>", 200);
    }

//    private abstract class AsyncRestRequest extends Response.Listener.Adapter
//    {
//        final Utf8StringBuilder _content = new Utf8StringBuilder();
//
//        AsyncRestRequest()
//        {
//        }
//
//        @Override
//        public void onContent(Response response, ByteBuffer content)
//        {
//            byte[] bytes = BufferUtil.toArray(content);
//            _content.append(bytes,0,bytes.length);
//        }
//
//        @Override
//        public void onComplete(Result result)
//        {
//            // extract auctions from the results
//            Map<String,?> query = (Map<String,?>) JSON.parse(_content.toString());
//            Object[] auctions = (Object[]) query.get("Item");
//            if (auctions != null)
//            {
//                for (Object o : auctions)
//                    onAuctionFound((Map<String,String>)o);
//            }
//            onComplete();
//
//        }
//
//        abstract void onAuctionFound(Map<String,String> details);
//        abstract void onComplete();
//
//    }

//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
//    {
//        doGet(request, response);
//    }

}
