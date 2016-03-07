package com.klead.hello_world_async.rest.client;

import org.apache.cxf.jaxrs.client.WebClient;

import javax.ws.rs.core.MediaType;

/**
 * Created by kafi on 07/03/2016.
 */
    public class Client {

    public static void main (String args[]){
        WebClient client = WebClient.create( "http://localhost:8080/cxf-async-ws/hello_world_rest_service/say_hello/"+System.getProperty("user.name") );
        String greeting = client.accept(MediaType.TEXT_PLAIN).get(String.class);
        System.out.println(greeting);
        client.close();
    }
}
