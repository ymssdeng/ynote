package com.ymss.ynote.endpoints;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.google.common.base.Joiner;
import com.ymss.ynote.note.Attachment;
import com.ymss.ynote.note.Notebook;
import com.ymss.ynote.search.query.NotebookQuerier;
import com.ymss.ynote.storage.NotebookStorage;

@Named
@Path("/notebook/")
public class NotebookEndpoints {

	@Inject
	private NotebookStorage nbs;
	@Inject
	private NotebookQuerier nq;

	@POST
	public Response createOne(@Context HttpServletRequest hsr,
			@Context UriInfo uriinfo,
			@HeaderParam("Authorization") String pAuthentication,
			@QueryParam("name") String name,
			@QueryParam("attname") String attname) throws Exception {
		Notebook nbook = new Notebook(name);
		Attachment attachment = new Attachment();
		attachment.setName(attname);
		nbook.addAttachment(attachment);
		nbs.save(nbook);
		return Response.ok().build();
	}
	
	@POST
	@Path("/{id}/attachment")
	public Response addAttachment(@Context HttpServletRequest hsr,
			@Context UriInfo uriinfo,
			@HeaderParam("Authorization") String pAuthentication,
			@PathParam("id") int id,
			@QueryParam("name") String attname,
			@QueryParam("extension") String attext) throws Exception {
		Notebook nbook = nbs.getById(id);
		Attachment attachment = new Attachment();
		attachment.setName(attname);
		nbook.addAttachment(attachment);
		nbs.save(nbook);
		return Response.ok().build();
	}

	@GET
	public Response getNotebook(@Context HttpServletRequest hsr,
			@Context UriInfo uriinfo,
			@HeaderParam("Authorization") String pAuthentication,
			@QueryParam("id") int id) throws Exception {
		Notebook nb = nbs.getById(id);
		return Response.ok(
				Joiner.on(' ').join(id, nb.getCategory().getName(),
						nb.getName())).build();
	}

	@GET
	@Path("query/")
	public Response queryNotebook(@Context HttpServletRequest hsr,
			@Context UriInfo uriinfo,
			@HeaderParam("Authorization") String pAuthentication,
			@QueryParam("query") String queryStr) throws Exception {

		
		List<Notebook> lst = nq.search(queryStr).result();

		StringBuilder sb = new StringBuilder();
		for (Notebook nb : lst) {
			sb.append(
					Joiner.on(' ').join(nb.getId(), nb.getCategory().getName(),
							nb.getName())).append("\n");
		}
		return Response.ok(sb.toString()).build();
	}
}
