package com.ymss.ynote.storage;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.ymss.ynote.note.Attachment;
import com.ymss.ynote.note.Notebook;
import com.ymss.ynote.storage.provider.AttachmentStorageProvider;
import com.ymss.ynote.storage.provider.NotebookStorageProvider;

@Named
public class NotebookStorage implements Storage<Notebook> {

	@Inject
	@Named("notebookMapper")
	private NotebookStorageProvider nbsp;
	@Inject
	@Named("attachmentMapper")
	private AttachmentStorageProvider asp;

	@Override
	public void save(Notebook notebook) throws Exception {
		// TODO: transaction
		nbsp.save(notebook);

		for (Attachment attachment : notebook.getAttachments()) {
			// set notebook
			attachment.setNotebook(notebook);
			asp.save(attachment);
		}

		// save attachment file
		ByteBuffer buffer = ByteBuffer.allocate(2048);
		for (Attachment attachment : notebook.getAttachments()) {
			Path apath = Paths.get("/home/" + attachment.getGuid());
			try (BufferedInputStream bis = new BufferedInputStream(
					attachment.getStream())) {
				Path path = Files.createFile(apath);

				int read = 0;
				while ((read = bis.read(buffer.array())) > 0) {
					Files.write(path, buffer.array(), StandardOpenOption.APPEND);
				}
			}
		}
		
		// TODO: send this to index thread
	}

	@Override
	public Notebook getById(int id) throws IOException {
		return nbsp.getById(id);
	}

	@Override
	public int getPageCount(int pageSize) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Notebook> getPage(Paging paging) throws FileNotFoundException,
			IOException {
		return nbsp.getPage(paging.getPage(), paging.getPageSize(),
				paging.isAscending());
	}

	@Override
	public boolean hasNextPage(Paging paging) throws FileNotFoundException,
			IOException {
		return nbsp.hasNextPage(paging.getPage(), paging.getPageSize()) > 0;
	}

}
