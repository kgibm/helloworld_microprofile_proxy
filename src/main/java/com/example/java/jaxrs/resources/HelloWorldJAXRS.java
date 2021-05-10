package com.example.java.jaxrs.resources;

import java.time.Instant;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("")
public class HelloWorldJAXRS {

	@Path("helloworldjaxrs")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String helloWorldJAXRS() {
		return "Hello World @ " + Instant.now();
	}
}
