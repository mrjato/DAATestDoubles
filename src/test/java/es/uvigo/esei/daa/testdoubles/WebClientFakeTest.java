package es.uvigo.esei.daa.testdoubles;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import org.eclipse.jetty.server.Server;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Miguel Reboiro Jato
 * @see http://download.eclipse.org/jetty/stable-9/apidocs/
 */
public class WebClientFakeTest implements WebClientTest {
	private WebClient client;
	private Server server;
	private String helloWorldURL;
	private String errorURL;
	
	@Before
	public void setUp() throws Exception {
		this.client = new WebClient();
		
		this.server = new Server(8888);

		final SimpleHandler handler = new SimpleHandler();
		handler.addMapping("/helloworld", "Hello World");
		
		this.server.setHandler(handler);
		
		this.server.start();
		
		this.helloWorldURL = "http://localhost:8888/helloworld";
		this.errorURL = "http://localhost:8888/missing";
	}

	@After
	public void tearDown() throws Exception {
		this.server.stop();
	}

	@Test
	public void testHelloWorld() throws IOException {
		final URL url = new URL(this.helloWorldURL);
		
		assertEquals(
			"Hello World",
			this.client.getContent(url)
		);
	}
	
	@Test(expected = FileNotFoundException.class)
	public void testError() throws IOException {
		final URL url = new URL(this.errorURL);
		
		assertNull(
			this.client.getContent(url)
		);
	}
}
