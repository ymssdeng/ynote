package com.ymss.ynote.storage.provider;

import java.io.IOException;

public interface IStorageProvider {

	void save (String path, String data) throws IllegalArgumentException, IOException;
	
	String get(String path) throws IOException;
}
