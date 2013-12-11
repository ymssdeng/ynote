package com.ymss.ynote.storage.provider;

import java.io.IOException;
import java.io.InputStream;

public interface StorageProvider {

	void saveText(String path, String text) throws IOException;

	void save(String path, InputStream in) throws IOException;

	String getText(String path) throws IOException;
}
