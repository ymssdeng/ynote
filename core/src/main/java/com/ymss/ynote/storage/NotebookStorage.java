package com.ymss.ynote.storage;

import javax.inject.Inject;
import javax.inject.Named;

import com.ymss.ynote.note.Notebook;

@Named
public class NotebookStorage implements INotebookStorage {

	@Inject
	private IStorageProvider sp;

	@Override
	public void save(Notebook notebook) throws Exception {
		sp.save("notebook/" + notebook.getCategory().toString() + "/"
				+ notebook.getId(), notebook.toJSON());
	}

}
