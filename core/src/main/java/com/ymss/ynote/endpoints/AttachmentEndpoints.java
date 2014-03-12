package com.ymss.ynote.endpoints;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.ymss.ynote.note.Attachment;
import com.ymss.ynote.storage.AttachmentStorage;

@Path("/notebook/")
public class AttachmentEndpoints {

	@Inject
	private AttachmentStorage as;

	@POST
	@Path("/{id}/attachment")
	public Response addAttachments(@Context HttpServletRequest hsr,
			@Context UriInfo uriinfo,
			@HeaderParam("Authorization") String pAuthentication,
			@PathParam("id") int id) throws Exception {
		String[] names = hsr.getParameterValues("name");
		String[] exts = hsr.getParameterValues("extension");

		if (names.length != exts.length)
			return Response.ok("request error").build();

		ServletFileUpload upload = new ServletFileUpload();
		FileItemIterator iterator = upload.getItemIterator(hsr);
		
		for (int i = 0; i < names.length; i++) {
			Attachment attachment = new Attachment();
			attachment.setName(names[i]);
			attachment.setExtension(exts[i]);
			attachment.setStream(iterator.next().openStream());
			as.save(attachment);
		}

		return Response.ok().build();
	}
}
