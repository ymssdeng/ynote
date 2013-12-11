package com.ymss.ynote.storage;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.ymss.ynote.note.Notebook;
import com.ymss.ynote.storage.provider.StorageProvider;

@Named
public class NotebookStorage implements Storage<Notebook> {

	@Inject
	private StorageProvider sp;

	@Override
	public void save(Notebook notebook) throws Exception {
		sp.saveText("notebook/" + notebook.getCategory().toString() + "/"
				+ notebook.getId(), notebook.toJSON());
	}

	@Override
	public Notebook getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPageCount(int pageSize) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Notebook> getPage(Paging paging) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasNextPage(Paging paging) {
		// TODO Auto-generated method stub
		return false;
	}

}
