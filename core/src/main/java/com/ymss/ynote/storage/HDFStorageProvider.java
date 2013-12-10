package com.ymss.ynote.storage;

import java.io.IOException;

import javax.inject.Named;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Value;

@Named
public class HDFStorageProvider implements IStorageProvider {

	private FileSystem hdfs = null;
	@Value("${hdfs.url}")
	private String hdfsUrl;

	public HDFStorageProvider() throws IOException {
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", hdfsUrl);
		hdfs = FileSystem.get(conf);
	}

	@Override
	public void save(String path, String data) throws IllegalArgumentException, IOException {

		try (FSDataOutputStream out = hdfs.create(new Path(path))) {
			out.write(data.getBytes());
		}
	}
}
