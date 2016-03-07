package com.klead.hello_world_async.client.callback;

import com.klead.hello_world_async.types.Greeting;

import javax.xml.ws.AsyncHandler;
import javax.xml.ws.Response;

/**
 * Created by kafi on 04/03/2016.
 */
public class HelloWorldAsyncHandler implements AsyncHandler<Greeting> {

    private Greeting reply;

    public void handleResponse(Response<Greeting> response) {
        try {
            System.err.println("handleResponse called");
            reply = response.get();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getResponse() {
        return reply.getGreeting();
    }
}
