package com.klead.hello_world_async;

import com.klead.hello_world_async.types.Greeting;

import javax.jws.WebService;
import javax.xml.ws.AsyncHandler;
import javax.xml.ws.Response;
import javax.xml.ws.ResponseWrapper;
import java.util.concurrent.Future;

/**
 * Created by kafi on 04/03/2016.
 */

@WebService(name = "sayHello")
public interface HelloWorld {


    @ResponseWrapper(localName = "sayHelloResponse", className = "com.klead.hello_world_async.types.Greeting")
    public Response<Greeting> sayHelloAsync(String name);


    @ResponseWrapper(localName = "sayHelloResponse", className = "com.klead.hello_world_async.types.Greeting")
    public Future<?> sayHelloAsync(String name, AsyncHandler<Greeting> asyncHandler);

    @ResponseWrapper(localName = "sayHelloResponse", className = "com.klead.hello_world_async.types.Greeting")
    public String sayHello(String name);
}
