package com.ymss.ynote.storage.provider;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Value;

@Named
public class HDFStorageProvider implements StorageProvider {

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
	public void saveText(String path, String text)
			throws IllegalArgumentException, IOException {
		if (path == null)
			return;

		try (FSDataOutputStream out = hdfs.create(new Path(path))) {
			if (text != null)
				out.write(text.getBytes());
		}
	}

	@Override
	public String getText(String path) throws IOException {
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

	@Override
	public void save(String path, InputStream in) throws IOException {
		if (path == null || path.isEmpty() || in == null)
			return;

		try (FSDataOutputStream out = hdfs.create(new Path(path))) {
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
		} finally {
			in.close();
		}
	}

}
