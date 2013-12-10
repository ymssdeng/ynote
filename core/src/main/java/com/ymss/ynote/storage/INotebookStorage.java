package com.ymss.ynote.storage;

import java.util.List;

import com.ymss.ynote.note.Notebook;

public interface INotebookStorage {

	void save(Notebook notebook) throws Exception;
	
	List<Notebook> get();
}
