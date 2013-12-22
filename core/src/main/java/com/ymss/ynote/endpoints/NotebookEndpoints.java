package com.ymss.ynote.endpoints;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.ymss.ynote.note.Notebook;
import com.ymss.ynote.note.NotebookCategory;
import com.ymss.ynote.storage.NotebookStorage;

@Named
@Path("/notebook/")
public class NotebookEndpoints {

	@Inject
	private NotebookStorage nbs;
	
	@POST
	public Response createOne(
			@Context HttpServletRequest hsr,
			@Context UriInfo uriinfo,
			@HeaderParam("Authorization") String pAuthentication,
			@QueryParam("name") String name
			) throws Exception
	{
		Notebook nbook = Notebook.newInstance(name);
		nbs.save(nbook);
		return Response.ok().build();
	}
	
	@GET
	public Response getNotebooks(
			@Context HttpServletRequest hsr,
			@Context UriInfo uriinfo,
			@HeaderParam("Authorization") String pAuthentication,
			@QueryParam("name") String name
			) throws Exception
	{
		Notebook nbook = Notebook.newInstance(name);
		NotebookCategory category = new NotebookCategory();
		category.setId(1);
		nbook.setCategory(category);
		
		nbs.save(nbook);
		return Response.ok().build();
	}
}
