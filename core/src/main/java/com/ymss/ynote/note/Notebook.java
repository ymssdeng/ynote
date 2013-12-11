package com.ymss.ynote.note;

import java.util.UUID;

import org.json.JSONObject;

import com.google.common.base.Strings;
import com.ymss.ynote.storage.Storageable;

public class Notebook implements Storageable<Notebook> {

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

	@Override
	public String toJSON() {
		JSONObject json = new JSONObject();
		json.append("id", id).append("name", name)
				.append("category", category.toString());
		return json.toString();
	}

	@Override
	public Notebook fromJSON(String text) {
		if (Strings.isNullOrEmpty(text))
			return this;

		JSONObject json = new JSONObject(text);
		id = json.getString("id");
		name = json.getString("name");
		category = NotebookCategory.valueOf(json.getString("category"));
		return this;
	}
}
