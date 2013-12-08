package com.ymss.ynote.storage;

import java.io.IOException;

public interface IStorageProvider {

	void save (String path, String data) throws IllegalArgumentException, IOException;
}
