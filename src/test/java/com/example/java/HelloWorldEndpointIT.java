package com.example.java;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class HelloWorldEndpointIT {
	private static Client client;

	@BeforeAll
	public static void setup() {
		client = ClientBuilder.newClient();
	}

	@Test
	public void test1() {
		WebTarget target = getTarget("/api/helloworld");
		try (Response response = target.request().get()) {
			assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
			String body = response.readEntity(String.class);
			System.out.println("/api/helloworld response: " + body);
			assertTrue(body.contains("Hello World"));
		}
	}

	private WebTarget getTarget(String path) {
		return client.target("http://localhost:" + System.getProperty("HTTP_PORT") + path);
	}

	@AfterAll
	public static void stop() {
		client.close();
	}
}
