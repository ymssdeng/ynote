package com.ymss.ynote.storage;

import java.util.List;

public interface Storage<T extends Storageable> {

	void save(T t) throws Exception;

	T getById(String id);

	int getPageCount(int pageSize);

	List<T> getPage(Paging paging);

	boolean hasNextPage(Paging paging);
}
