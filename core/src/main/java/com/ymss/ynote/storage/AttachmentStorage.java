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
import com.ymss.ynote.search.tika.TextExtractor;
import com.ymss.ynote.storage.provider.AttachmentStorageProvider;

@Named
public class AttachmentStorage implements Storage<Attachment>{

	@Inject
	@Named("attachmentMapper")
	private AttachmentStorageProvider asp;
	
	@Inject
	@Named
	private TextExtractor te;
	
	@Override
	public void save(Attachment t) throws Exception {
		asp.save(t);
		
		// save attachment file
		ByteBuffer buffer = ByteBuffer.allocate(2048);
		Path apath = Paths.get("/home/" + t.getGuid());
		try(BufferedInputStream bis = new BufferedInputStream(t.getStream())){
			Path path = Files.createFile(apath);
			
			int read = 0;
			while((read = bis.read(buffer.array())) > 0){
				Files.write(path, buffer.array(), StandardOpenOption.APPEND);
			}
		};
		
		// put into extractor queue
		te.put(apath, t.getExtension());
	}

	@Override
	public Attachment getById(int id) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPageCount(int pageSize) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Attachment> getPage(Paging paging)
			throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasNextPage(Paging paging) throws FileNotFoundException,
			IOException {
		// TODO Auto-generated method stub
		return false;
	}

}
