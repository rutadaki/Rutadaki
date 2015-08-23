package org.fewnuts.rutadaki.api.auth;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.sun.jersey.api.container.ContainerException;
import com.sun.jersey.core.util.ReaderWriter;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;

public class AuthApiRequestFilter implements ContainerRequestFilter {

	private final static Logger log = Logger
			.getLogger(AuthApiRequestFilter.class.getName());

	@Override
	public ContainerRequest filter(ContainerRequest request) {
		String path = request.getPath();
		log.info("Filtering request path: " + path);

		// IMPORTANT!!! First, Acknowledge any pre-flight test from browsers for
		// this case before validating the headers (CORS stuff)
		if (request.getMethod().equals("OPTIONS")) {
			log.info("en Options?");
			ResponseBuilder builder = null;
			String response = "OK";
			builder = Response.status(Response.Status.OK).entity(response);
			throw new WebApplicationException(builder.build());

		}
		
		// Then check is the service key exists and is valid.
		Authenticator demoAuthenticator = Authenticator.getInstance();
		String serviceKey = request.getHeaderValue(HttpHeaderNames.SERVICE_KEY);

		if (!demoAuthenticator.isServiceKeyValid(serviceKey)) {
			ResponseBuilder builder = null;
			String response = "Invalid Service Key";
			builder = Response.status(Response.Status.UNAUTHORIZED).entity(
					response);
			throw new WebApplicationException(builder.build());
		}

		// For any pther methods besides login, the authToken must be verified
		if (!path.startsWith("auth/login")) {
			String authToken = request
					.getHeaderValue(HttpHeaderNames.AUTH_TOKEN);

			// if it isn't valid, just kick them out.
			if (!demoAuthenticator.isAuthTokenValid(serviceKey, authToken)) {
				ResponseBuilder builder = null;
				String response = "Authentication is need";
				builder = Response.status(Response.Status.UNAUTHORIZED).entity(
						response);
				throw new WebApplicationException(builder.build());
			}
		}
		//read(request);
		
		return request;
	}
	
	
	//TODO used to see the requests content...
	@SuppressWarnings("unused")
	private ContainerRequest read(ContainerRequest request) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        InputStream in = request.getEntityInputStream();
        final StringBuilder b = new StringBuilder();
        try {
            if (in.available() > 0) {
                ReaderWriter.writeTo(in, out);

                byte[] requestEntity = out.toByteArray();
                printEntity(b, requestEntity);

                request.setEntityInputStream(new ByteArrayInputStream(requestEntity));
            }
            return request;
        } catch (IOException ex) {
            throw new ContainerException(ex);
        }

    }

    private void printEntity(StringBuilder b, byte[] entity) throws IOException {
        if (entity.length == 0)
            return;
        b.append(new String(entity)).append("\n");
        System.out.println("#### Intercepted Entity ####");
        System.out.println(b.toString());
    }
}