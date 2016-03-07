package com.klead.hello_world_async;

import com.klead.hello_world_async.types.Greeting;
import org.apache.cxf.annotations.UseAsyncMethod;
import org.apache.cxf.jaxws.ServerAsyncResponse;

import javax.jws.WebService;
import javax.xml.ws.AsyncHandler;
import javax.xml.ws.Response;
import java.util.concurrent.Future;

/**
 * Created by kafi on 04/03/2016.
 */
@WebService(endpointInterface = "com.klead.hello_world_async.HelloWorld")
public class HelloWorldImpl implements  HelloWorld{

    public Response<Greeting> sayHelloAsync(String name) {
        return null;
    }

    public Future<?> sayHelloAsync(final String name,final AsyncHandler<Greeting> asyncHandler) {
        System.out.println("Executing operation asynchronously\n");
        final ServerAsyncResponse<Greeting> asyncResponse = new ServerAsyncResponse<Greeting>();
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Greeting resp = new Greeting();
                resp.setGreeting("How are you " + name);
                asyncResponse.set(resp);
                System.out.println("Responding on background thread\n");
                asyncHandler.handleResponse(asyncResponse);
            }
        }.start();

        return asyncResponse;
    }

    @UseAsyncMethod
    public String sayHello(String name) {
        System.out.println("Executing operation synchronously\n");
        return "How are you " + name;
    }
}
