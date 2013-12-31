package com.ymss.ynote.note;

import java.sql.Timestamp;

public class Notebook implements Storagable{

	private static final String DEFAULT_NAME = "New Notebook";

	private Integer id;
	private String name = DEFAULT_NAME;
	private NotebookCategory category;
	private Timestamp timestamp;

	public Notebook() {
		this(null);
	}

	public Notebook(String name) {
		this.name = (name != null && !name.isEmpty()) ? name : DEFAULT_NAME;
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

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
}
