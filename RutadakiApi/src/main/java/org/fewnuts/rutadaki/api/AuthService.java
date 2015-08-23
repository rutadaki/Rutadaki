package org.fewnuts.rutadaki.api;

import java.security.GeneralSecurityException;

import javax.security.auth.login.LoginException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.fewnuts.rutadaki.api.auth.Authenticator;
import org.fewnuts.rutadaki.api.auth.HttpHeaderNames;
import org.json.JSONObject;

@Path("/auth")
public class AuthService {
	
	/**
	 * Performs the login of the API user 
	 */
	@POST
    @Path( "login" )
    @Produces( MediaType.APPLICATION_JSON )
	@Consumes( MediaType.APPLICATION_JSON )
    public Response login(
        @Context HttpHeaders httpHeaders, String body
        /*@FormParam( "username" ) String username,
        @FormParam( "password" ) String password */) {
		
		JSONObject requestJson = new JSONObject(body);
		
		String username = requestJson.getString("username");
        String password = requestJson.getString("password");
        
        //System.out.println(body);
		
        Authenticator demoAuthenticator = Authenticator.getInstance();
        String serviceKey = httpHeaders.getRequestHeader( HttpHeaderNames.SERVICE_KEY ).get(0);
 
        try {
            String authToken = demoAuthenticator.login( serviceKey, username, password );
            JSONObject jsonObj =  new JSONObject();//.fromObject(new HashMap<Object, Object>());
            jsonObj.put( "auth_token", authToken );
            jsonObj.put( "success", true );
 
            return getNoCacheResponseBuilder( Response.Status.OK ).entity( jsonObj.toString() ).build();
 
        } catch ( final LoginException ex ) {
        	
        	System.out.println("Login failed");
        	
            JSONObject jsonObj =  new JSONObject();//JSONObject.fromObject(new HashMap<Object, Object>());
            jsonObj.put( "message", "Problem matching service key, username and password" );
            return getNoCacheResponseBuilder( Response.Status.UNAUTHORIZED ).entity( jsonObj.toString() ).build();
        }
    }
	
	/**
	 * Logouts the API user
	 * 
	 * @return
	 */
	@POST
    @Path( "logout" )
    public Response logout(
        @Context HttpHeaders httpHeaders ) {
        try {
            Authenticator demoAuthenticator = Authenticator.getInstance();
            String serviceKey = httpHeaders.getRequestHeader( HttpHeaderNames.SERVICE_KEY ).get(0);
            String authToken = httpHeaders.getRequestHeader(HttpHeaderNames.AUTH_TOKEN ).get(0);
 
            demoAuthenticator.logout( serviceKey, authToken );
 
            return getNoCacheResponseBuilder( Response.Status.NO_CONTENT ).build();
        } catch ( final GeneralSecurityException ex ) {
            return getNoCacheResponseBuilder( Response.Status.INTERNAL_SERVER_ERROR ).build();
        }
    }
 
    private Response.ResponseBuilder getNoCacheResponseBuilder( Response.Status status ) {
        CacheControl cc = new CacheControl();
        cc.setNoCache( true );
        cc.setMaxAge( -1 );
        cc.setMustRevalidate( true );
 
        return Response.status( status ).cacheControl( cc );
    }
}