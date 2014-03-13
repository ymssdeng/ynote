package com.ymss.ynote.search.tika;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.inject.Named;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;

@Named
public class TextExtractorService implements TextExtractor {

	@Override
	public void put(Path path, String ext) {
		if (!Files.exists(path))
			return;

		try {
			AutoDetectParser parser = new AutoDetectParser();
			BodyContentHandler handler = new BodyContentHandler(5000 * 7);

			FileInputStream fis = new FileInputStream(path.toFile());
			Metadata metadata = new Metadata();
			parser.parse(fis, handler, metadata);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}
