package com.example.java.jaxrs.resources;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("")
public class HelloWorldJAXRS {

	@Path("helloworldjaxrs")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> helloWorldJAXRS() {
		Map<String, Object> result = new HashMap<>();
		result.put("result", "Hello World");
		return result;
	}
}
