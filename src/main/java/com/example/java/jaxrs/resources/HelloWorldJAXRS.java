package com.example.java.jaxrs.resources;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("")
public class HelloWorldJAXRS {

	private static final String CLASS_NAME = HelloWorldJAXRS.class.getCanonicalName();
	private static final Logger LOG = Logger.getLogger(CLASS_NAME);
	private static final String METHOD_HELLOWORLDJAXRS = "helloWorldJAXRS";

	@Path("helloworld")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> helloWorldJAXRS(@DefaultValue("0") @QueryParam("sleepTimeMs") long sleepTimeMs)
			throws Throwable {

		if (LOG.isLoggable(Level.FINER))
			LOG.entering(CLASS_NAME, METHOD_HELLOWORLDJAXRS);

		Map<String, Object> json = new HashMap<>();
		json.put("message", "Hello World @ " + Instant.now());

		if (sleepTimeMs > 0) {
			Thread.sleep(sleepTimeMs);
		}

		if (LOG.isLoggable(Level.FINER))
			LOG.exiting(CLASS_NAME, METHOD_HELLOWORLDJAXRS, json);

		return json;
	}
}
