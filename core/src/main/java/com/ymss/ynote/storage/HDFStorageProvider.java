package com.ymss.ynote.storage;

import java.io.IOException;

import javax.inject.Named;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

@Named
public class HDFStorageProvider implements IStorageProvider {

	private FileSystem hdfs = null;

	public HDFStorageProvider() throws IOException {
		Configuration conf = new Configuration();
		hdfs = FileSystem.getLocal(conf);
	}

	@Override
	public void save(String path, String data) throws IllegalArgumentException, IOException {

		try (FSDataOutputStream out = hdfs.create(new Path(path))) {
			out.write(data.getBytes());
		}
	}
}
