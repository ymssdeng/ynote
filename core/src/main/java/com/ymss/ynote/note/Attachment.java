package com.ymss.ynote.note;

import java.io.InputStream;
import java.sql.Timestamp;

public class Attachment implements Storagable {

	private Integer id;
	private String guid;
	private Notebook notebook;
	private String name;
	private String extension;
	private Timestamp createdStamp;
	private Timestamp updatedStamp;
	private InputStream stream;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Notebook getNotebook() {
		return notebook;
	}

	public void setNotebook(Notebook notebook) {
		this.notebook = notebook;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getCreatedStamp() {
		return createdStamp;
	}

	public void setCreatedStamp(Timestamp createdStamp) {
		this.createdStamp = createdStamp;
	}

	public Timestamp getUpdatedStamp() {
		return updatedStamp;
	}

	public void setUpdatedStamp(Timestamp updatedStamp) {
		this.updatedStamp = updatedStamp;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public InputStream getStream() {
		return stream;
	}

	public void setStream(InputStream stream) {
		this.stream = stream;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}



}
