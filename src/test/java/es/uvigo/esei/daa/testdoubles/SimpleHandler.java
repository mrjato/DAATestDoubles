package es.uvigo.esei.daa.testdoubles;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

class SimpleHandler extends AbstractHandler {
	private final Map<String, String> mapping;
	
	public SimpleHandler() {
		this.mapping = new HashMap<String, String>();
	}
	
	public void addMapping(String key, String content) {
		this.mapping.put(key, content);
	}
	
	@Override
	public void handle(
		String target,
		Request baseRequest, 
		HttpServletRequest request,
		HttpServletResponse response
	) throws IOException, ServletException {
		response.setContentType("text/html;charset=utf-8");
		baseRequest.setHandled(true);
		
		if (this.mapping.containsKey(target)) {
			response.setStatus(HttpServletResponse.SC_OK);

			final PrintWriter writer = response.getWriter();
			writer.write(this.mapping.get(target));
			writer.flush();
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
	}
}