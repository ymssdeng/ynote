package com.ymss.ynote.storage.provider;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.ymss.ynote.storage.Paging;

public interface StorageProvider {

	void saveText(String path, String text) throws IOException;

	void save(String path, InputStream in) throws IOException;

	String getText(String path) throws IOException;

	boolean exists(String path) throws IOException;

	int getFileCount(String path) throws FileNotFoundException, IOException;

	List<String> getFileRange(String path, Paging paging)
			throws FileNotFoundException, IOException;

}
