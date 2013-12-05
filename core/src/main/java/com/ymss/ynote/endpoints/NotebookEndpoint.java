package com.ymss.ynote.endpoints;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;

import com.ymss.ynote.note.Notebook;
import com.ymss.ynote.storage.INotebookStorage;

@Path("/note/")
public class NotebookEndpoint {

	@Inject
	private INotebookStorage notebookStorage;
	
	@POST
	public Response createOne(
			@Context HttpServletRequest hsr,
			@Context UriInfo uriinfo,
			@HeaderParam("Authorization") String pAuthentication,
			@QueryParam("name") String name
			) throws Exception
	{
		Notebook nbook = Notebook.newInstance(name);
		notebookStorage.save(nbook);
		return Response.ok().build();
	}
}
