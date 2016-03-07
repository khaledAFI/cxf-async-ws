package com.klead.hello_world_async.client.polling;

import com.klead.hello_world_async.HelloWorld;
import com.klead.hello_world_async.types.Greeting;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import javax.xml.ws.Response;


/**
 * Created by kafi on 04/03/2016.
 */
public class Client {



    private Client() {
    }

    public static void main(String args[]) throws Exception {

        // Polling approach:
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

        factory.setServiceClass(HelloWorld.class);
        factory.setAddress("http://localhost:8080/cxf-async-ws/helloWorld?wsdl");
        factory.getInInterceptors().add(new LoggingInInterceptor());
        factory.getOutInterceptors().add(new LoggingOutInterceptor());
        HelloWorld client = (HelloWorld) factory.create();
        // polling method
        System.out.println("Invoking changeStudentAsync using polling...");
        Response<Greeting> changeStudentResp = client.sayHelloAsync(System.getProperty("user.name"));
        while (true) {
            if (changeStudentResp.isDone()) {
                Greeting reply = changeStudentResp.get();
                System.out.println("Server responded through polling with: "+ reply.getGreeting());
                break;
            } else {
                Thread.sleep(1000);
                System.out.println("Polling #####################");
            }
        }
        System.exit(0);
    }
}
