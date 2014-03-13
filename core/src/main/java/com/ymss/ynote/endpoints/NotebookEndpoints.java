package com.ymss.ynote.endpoints;

import java.util.List;

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

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

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
			@QueryParam("name") String name) throws Exception {
		String[] names = hsr.getParameterValues("name");
		String[] exts = hsr.getParameterValues("extension");

		if (names.length != exts.length)
			return Response.ok("request error").build();

		// get stream
		ServletFileUpload upload = new ServletFileUpload();
		FileItemIterator iterator = upload.getItemIterator(hsr);
		
		for (int i = 0; i < names.length; i++) {
			Attachment attachment = new Attachment();
			attachment.setName(names[i]);
			attachment.setExtension(exts[i]);
			attachment.setStream(iterator.next().openStream());
			as.save(attachment);
		}
		
		Notebook nbook = new Notebook(name);
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
