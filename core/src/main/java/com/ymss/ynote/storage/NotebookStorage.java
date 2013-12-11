package com.ymss.ynote.storage;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.ymss.ynote.note.Notebook;
import com.ymss.ynote.storage.provider.IStorageProvider;

@Named
public class NotebookStorage implements INotebookStorage {

	@Inject
	private IStorageProvider sp;

	@Override
	public void save(Notebook notebook) throws Exception {
		sp.save("notebook/" + notebook.getCategory().toString() + "/"
				+ notebook.getId(), notebook.toJSON());
	}

	@Override
	public List<Notebook> get() {
		// TODO Auto-generated method stub
		return null;
	}

}
