package com.ymss.ynote.note;

public class NotebookCategory implements Storagable{
	private Integer id;
	private String name;

	public int getId() {
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
}
