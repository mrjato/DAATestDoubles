package es.uvigo.esei.daa.testdoubles;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.powermock.api.easymock.PowerMock.createMock;
import static org.powermock.api.easymock.PowerMock.replay;
import static org.powermock.api.easymock.PowerMock.verify;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(WebClient.class)
public class WebClientMockTest implements WebClientTest {
	private WebClient client;
	private URL mockURL;
	
	@Before
	public void setUp() throws Exception {
		this.mockURL = createMock(URL.class);
		this.client = new WebClient();
	}
	
	@After
	public void tearDown() throws Exception {
		verify(this.mockURL);
	}

	@Test
	public void testHelloWorld() throws IOException {
		expect(this.mockURL.openStream())
			.andReturn(new ByteArrayInputStream("Hello World".getBytes()));
		
		replay(this.mockURL);
		
		assertEquals(
			"Hello World",
			this.client.getContent(this.mockURL)
		);
	}
	
	@Test(expected = FileNotFoundException.class)
	public void testError() throws IOException {
		expect(this.mockURL.openStream())
			.andThrow(new FileNotFoundException());
		
		replay(this.mockURL);
		
		assertNull(
			this.client.getContent(this.mockURL)
		);
	}
}
