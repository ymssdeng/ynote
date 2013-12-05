package com.ymss.ynote.endpoints;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

@Path("/note/")
public class NoteEndpoints {
	
	@POST
	public Response createOne(
			@Context UriInfo uriinfo,
			@HeaderParam("Authorization") String pAuthentication,
			@QueryParam("subject") String subject,
			@QueryParam("body") String body) throws Exception
	{
		return null;
	}
}
