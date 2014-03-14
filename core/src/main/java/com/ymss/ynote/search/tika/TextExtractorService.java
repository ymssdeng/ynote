package com.ymss.ynote.search.tika;

import java.io.FileInputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.inject.Named;

import org.apache.tika.Tika;
import org.apache.tika.metadata.Metadata;

@Named
public class TextExtractorService implements TextExtractor {

	@Override
	public Reader extract(Path path, String ext) {
		if (!Files.exists(path))
			return null;

		Reader reader = null;
		try {
			// BodyContentHandler handler = new BodyContentHandler(5000 * 7);
			FileInputStream fis = new FileInputStream(path.toFile());
			Metadata metadata = new Metadata();
			Tika tika = new Tika();
			reader = tika.parse(fis, metadata);
		} catch (Exception e) {
			// TODO: handle exception
		}

		// reader is convenient for index
		return reader;
	}
}
