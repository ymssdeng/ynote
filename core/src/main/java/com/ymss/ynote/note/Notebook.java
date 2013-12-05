package com.ymss.ynote.note;

import java.util.UUID;

public class Notebook{

	private static final String DEFAULT_NAME = "New Notebook";
	private String name = DEFAULT_NAME;
	private String id = UUID.randomUUID().toString();
	private NotebookCategory category = NotebookCategory.DEFAULT;

	private Notebook() {
	}

	private Notebook(String name) {
		this.name = (name != null && !name.isEmpty()) ? name : DEFAULT_NAME;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public static Notebook newInstance() {
		return new Notebook();
	}

	public static Notebook newInstance(String name) {
		return new Notebook(name);
	}
}
