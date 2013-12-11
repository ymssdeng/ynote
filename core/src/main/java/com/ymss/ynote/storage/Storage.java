package com.ymss.ynote.storage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface Storage<T extends Storageable<T>> {

	void save(T t) throws Exception;

	T getById(String id) throws IOException;

	int getPageCount(int pageSize);

	List<T> getPage(Paging paging) throws FileNotFoundException, IOException;

	boolean hasNextPage(Paging paging) throws FileNotFoundException, IOException;
}
