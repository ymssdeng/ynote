package com.ymss.ynote.search.tika;

import java.nio.file.Path;

public interface TextExtractor {

	void put(Path path, String ext);
}
