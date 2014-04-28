package es.uvigo.esei.daa.testdoubles;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

/*
 * Interface just to ensure that both WebClientFakeTest and WebClientMockTest
 * implement the same tests.
 */
public interface WebClientTest {
	@Test
	public void testHelloWorld() throws IOException;
	

	@Test(expected = FileNotFoundException.class)
	public void testError() throws IOException;
}
