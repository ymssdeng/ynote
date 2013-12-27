package com.ymss.ynote.storage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.ymss.ynote.note.Notebook;
import com.ymss.ynote.storage.provider.NotebookStorageProvider;

@Named
public class NotebookStorage implements Storage<Notebook> {

	@Inject
	@Named("notebookMapper")
	private NotebookStorageProvider nbsp;

	@Override
	public void save(Notebook notebook) throws Exception {
		nbsp.save(notebook);
	}

	@Override
	public Notebook getById(int id) throws IOException {
		return nbsp.getById(id);
	}

	@Override
	public int getPageCount(int pageSize) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Notebook> getPage(Paging paging) throws FileNotFoundException,
			IOException {
		return nbsp.getPage(paging.getPage(), paging.getPageSize(), paging.isAscending());
	}

	@Override
	public boolean hasNextPage(Paging paging) throws FileNotFoundException,
			IOException {
		return nbsp.hasNextPage(paging.getPage(), paging.getPageSize()) > 0;
	}

}
