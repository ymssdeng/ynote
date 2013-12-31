package com.ymss.ynote.storage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.ymss.ynote.note.Storagable;

public interface Storage<T extends Storagable> {

	void save(T t) throws Exception;

	T getById(int id) throws IOException;

	int getPageCount(int pageSize);

	List<T> getPage(Paging paging) throws FileNotFoundException, IOException;

	boolean hasNextPage(Paging paging) throws FileNotFoundException,
			IOException;
}
