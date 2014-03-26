package com.ymss.ynote.search.index;

import com.ymss.ynote.note.Notebook;

public class IndexCommand {

	private Notebook notebook;
	private IndexCommandType type;

	public Notebook getNotebook() {
		return notebook;
	}

	public void setNotebook(Notebook notebook) {
		this.notebook = notebook;
	}

	public IndexCommandType getType() {
		return type;
	}

	public void setType(IndexCommandType type) {
		this.type = type;
	}
}
