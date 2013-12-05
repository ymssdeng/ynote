package com.ymss.ynote.endpoints;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.ymss.ynote.storage.INotebookStorage;

@Path("/note/")
public class NotebookEndpoint {

	@Inject
	private INotebookStorage notebookStorage;
	
	@POST
	public Response createOne(
			@Context UriInfo uriinfo,
			@HeaderParam("Authorization") String pAuthentication,
			@QueryParam("name") String name
			) throws Exception
	{
		return null;
	}
}
