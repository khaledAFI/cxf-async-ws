package com.klead.hello_world_async.client.callback;

import com.klead.hello_world_async.HelloWorld;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import java.util.concurrent.Future;

/**
 * Created by kafi on 04/03/2016.
 */
public class Client {
    public static void main(String args[]) throws Exception {

        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

        factory.setServiceClass(HelloWorld.class);
        factory.setAddress("http://localhost:8080/cxf-async-ws/helloWorld?wsdl");
        factory.getInInterceptors().add(new LoggingInInterceptor());
        factory.getOutInterceptors().add(new LoggingOutInterceptor());
        HelloWorld client = (HelloWorld) factory.create();


        // callback method
        HelloWorldAsyncHandler hwAsyncHandler = new HelloWorldAsyncHandler();
        System.out.println("Invoking  using callback object...");
        Future<?> response = client.sayHelloAsync(
                System.getProperty("user.name"), hwAsyncHandler);
        while (!response.isDone()) {
            Thread.sleep(100);
        }
        String resp = hwAsyncHandler.getResponse();
        System.out.println("Server responded through callback with: " + resp);

        System.exit(0);
    }
}
