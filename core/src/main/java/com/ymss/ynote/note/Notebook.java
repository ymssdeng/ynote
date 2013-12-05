package com.ymss.ynote.note;

public class Notebook{

	private static final String DEFAULT_NAME = "New Notebook";
	private String name = DEFAULT_NAME;
	private NoteboolCategory category = NoteboolCategory.DEFAULT;

	private Notebook() {
	}

	private Notebook(String name) {
		this.name = (name != null && !name.isEmpty()) ? name : DEFAULT_NAME;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public NoteboolCategory getCategory() {
		return category;
	}

	public void setCategory(NoteboolCategory category) {
		this.category = category;
	}

	public static Notebook newInstance() {
		return new Notebook();
	}

	public static Notebook newInstance(String name) {
		return new Notebook(name);
	}
}
