package com.ymss.ynote.search.tika;

import java.io.Reader;
import java.nio.file.Path;

public interface TextExtractor {

	Reader extract(Path path, String ext);
}
