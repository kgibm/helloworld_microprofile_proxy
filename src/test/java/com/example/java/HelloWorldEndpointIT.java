package com.example.java;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.junit.jupiter.api.Test;

public class HelloWorldEndpointIT {
	@Test
	public void test1() {
		WebTarget target = getTarget("/api/helloworldjaxrs");
		try (Response response = target.request().get()) {
			assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
			assertTrue(response.readEntity(String.class).contains("Hello World"));
		}
	}

	private WebTarget getTarget(String path) {
		Client client = ClientBuilder.newBuilder().build();
		WebTarget target = client.target("http://localhost:" + System.getProperty("http.port") + path);
		return target;
	}
}
