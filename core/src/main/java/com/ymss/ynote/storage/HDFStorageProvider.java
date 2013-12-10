package com.ymss.ynote.storage;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Value;

@Named
public class HDFStorageProvider implements IStorageProvider {

	private FileSystem hdfs = null;
	@Value("${hdfs.url}")
	private String hdfsUrl;

	@PostConstruct
	public void start() {
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", hdfsUrl);
		// hdfs = FileSystem.get(conf);
	}

	public String getHdfsUrl() {
		return hdfsUrl;
	}

	public void setHdfsUrl(String hdfsUrl) {
		this.hdfsUrl = hdfsUrl;
	}

	@Override
	public void save(String path, String data) throws IllegalArgumentException,
			IOException {

		try (FSDataOutputStream out = hdfs.create(new Path(path))) {
			out.write(data.getBytes());
		}
	}

	@Override
	public String get(String path) throws IOException {
		Path hPath = new Path(path);

		try (FSDataInputStream in = hdfs.open(hPath)) {
			StringBuilder sb = new StringBuilder();
			byte[] buffer = new byte[1024];
			while (in.read(buffer) > 0) {
				sb.append(new String(buffer));
			}
			return sb.toString();
		}
	}
}
