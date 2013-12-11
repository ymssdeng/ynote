package com.ymss.ynote.storage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.ymss.ynote.note.Notebook;
import com.ymss.ynote.note.NotebookCategory;
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
	public Notebook getById(String id) throws IOException {
		String rootpath = "notebook/";

		// search for each category
		for (NotebookCategory category : NotebookCategory.values()) {
			String path = rootpath + category.toString() + "/" + id;
			if (sp.exists(path))
				return Notebook.newInstance().fromJSON(sp.getText(path));
		}

		return null;
	}

	@Override
	public int getPageCount(int pageSize) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Notebook> getPage(Paging paging) throws FileNotFoundException,
			IOException {
		List<Notebook> notebooks = new ArrayList<>();
		for (String path : sp.getFileRange("notebook/", paging)) {
			notebooks.add(Notebook.newInstance().fromJSON(sp.getText(path)));
		}
		return notebooks;
	}

	@Override
	public boolean hasNextPage(Paging paging) throws FileNotFoundException,
			IOException {
		if (paging == null)
			return false;

		return paging.getPage() * paging.getPageSize() < sp
				.getFileCount("notebook/");
	}

}
