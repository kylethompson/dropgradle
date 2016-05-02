package uk.co.kylethompson.gocdexample.resources;

import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class HelloResource {

    @GET
    @Timed
    @Produces(MediaType.TEXT_PLAIN)
    public String getStatus() {
        return "Hello from Backend Service!";
    }
}
