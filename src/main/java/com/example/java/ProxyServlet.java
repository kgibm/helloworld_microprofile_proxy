package com.example.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

@WebServlet("/ProxyServlet")
public class ProxyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String CLASS_NAME = ProxyServlet.class.getCanonicalName();
	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(CLASS_NAME);

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		performTask(request, response);
	}

	public void performTask(javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("<html><title>Proxy Servlet</title><body>");
		out.println("<h1>Proxy Servlet</h1>\n\n");

		String test = request.getParameter("test");
		if (test != null) {
			if (test.equals("clientBuilderAuthenticator")) {
				clientBuilderAuthenticator(out);
			}
			if (test.equals("clientBuilderProperties")) {
				clientBuilderProperties(out);
			}
			if (test.equals("systemProperties")) {
				systemProperties(out);
			}
		}

		out.println("\n\n</body></html>");
	}

	private String getProxyHost() {
		return System.getenv("PROXY_HOST");
	}

	private String getProxyPort() {
		return System.getenv("PROXY_PORT");
	}

	private String getProxyUser() {
		return System.getenv("PROXY_USER");
	}

	private String getProxyPassword() {
		return System.getenv("PROXY_PASSWORD");
	}

	private String getTargetURI() {
		return System.getenv("PROXY_TARGET");
	}

	private String getResponsePrefix() {
		return "<p>Response:</p>\n\n<pre>\n";
	}

	private String getResponseSuffix() {
		return "\n</pre>";
	}

	public void clientBuilderProperties(PrintWriter out) {
		ClientBuilder cb = ClientBuilder.newBuilder();
		cb.property("com.ibm.ws.jaxrs.client.proxy.type", "HTTP");
		cb.property("com.ibm.ws.jaxrs.client.proxy.host", getProxyHost());
		cb.property("com.ibm.ws.jaxrs.client.proxy.port", getProxyPort());
		cb.property("com.ibm.ws.jaxrs.client.proxy.username", getProxyUser());
		cb.property("com.ibm.ws.jaxrs.client.proxy.password", getProxyPassword());
		Client c = cb.build();
		String jaxresponse = c.target(getTargetURI()).request().get(String.class);
		out.println(getResponsePrefix() + jaxresponse + getResponseSuffix());
	}

	public void clientBuilderAuthenticator(PrintWriter out) {
		Authenticator authenticator = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return (new PasswordAuthentication(getProxyUser(), getProxyPassword().toCharArray()));
			}
		};
		Authenticator.setDefault(authenticator);
		ClientBuilder cb = ClientBuilder.newBuilder();
		cb.property("com.ibm.ws.jaxrs.client.proxy.type", "HTTP");
		cb.property("com.ibm.ws.jaxrs.client.proxy.host", getProxyHost());
		cb.property("com.ibm.ws.jaxrs.client.proxy.port", getProxyPort());
		Client c = cb.build();
		String jaxresponse = c.target(getTargetURI()).request().get(String.class);
		out.println(getResponsePrefix() + jaxresponse + getResponseSuffix());
	}

	public void systemProperties(PrintWriter out) throws IOException {
		System.setProperty("https.proxyHost", getProxyHost());
		System.setProperty("https.proxyPort", getProxyPort());

		Authenticator authenticator = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return (new PasswordAuthentication(getProxyUser(), getProxyPassword().toCharArray()));
			}
		};
		Authenticator.setDefault(authenticator);

		URL u = new URL(getTargetURI());
		HttpURLConnection con = (HttpURLConnection) u.openConnection();
		try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
			String inputLine;
			out.print(getResponsePrefix());
			while ((inputLine = in.readLine()) != null) {
				out.println(inputLine);
			}
			out.print(getResponseSuffix());
		}
	}
}
