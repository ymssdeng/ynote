package com.ymss.ynote.storage.provider;

import java.util.List;

import com.ymss.ynote.note.Attachment;
import com.ymss.ynote.note.Notebook;

public interface AttachmentStorageProvider {

	Attachment getById(int id);
	
	void save(Attachment attachment);
	
	void saveAttachmentsInNotebook(Notebook notebook);
	
	List<Attachment> getAttachmentsInNotebook(int notebookId);
}
