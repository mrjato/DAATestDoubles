package es.uvigo.esei.daa.testdoubles;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class WebClient {
	public String getContent(URL url) throws IOException {
		try (InputStreamReader isr = new InputStreamReader(url.openStream())) {
			final StringBuilder sb = new StringBuilder();
			
			final char[] buffer = new char[2048];
			
			int count;
			while ((count = isr.read(buffer)) != -1) {
				sb.append(buffer, 0, count);
			}
			
			return sb.toString();
		}
	}
}
