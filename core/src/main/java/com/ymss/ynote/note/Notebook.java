package com.ymss.ynote.note;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class Notebook implements Storagable{

	private static final String DEFAULT_NAME = "New Notebook";

	private Integer id;
	private String name = DEFAULT_NAME;
	private NotebookCategory category;
	private Timestamp createdStamp;
	private Timestamp updatedStamp;
	private Set<Attachment> attachments = new HashSet<>();

	public Notebook() {
		this(null);
	}

	public Notebook(String name) {
		this.name = (name != null && !name.isEmpty()) ? name : DEFAULT_NAME;
		this.category = new NotebookCategory();
		this.category.setId(1);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public NotebookCategory getCategory() {
		return category;
	}

	public void setCategory(NotebookCategory category) {
		this.category = category;
	}

	public Timestamp getCreatedStamp() {
		return createdStamp;
	}

	public void setCreatedStamp(Timestamp timestamp) {
		this.createdStamp = timestamp;
	}
	
	public Timestamp getUpdatedStamp() {
		return updatedStamp;
	}

	public void setUpdatedStamp(Timestamp timestamp) {
		this.updatedStamp = timestamp;
	}

	public Set<Attachment> getAttachments() {
		return attachments;
	}
	
	public void addAttachment(Attachment attachment) {
		attachments.add(attachment);
	}
}
