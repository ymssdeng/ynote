package com.ymss.ynote.storage.provider;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.inject.Named;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.springframework.beans.factory.annotation.Value;

import com.google.common.base.Strings;
import com.ymss.ynote.storage.Paging;

@Named
@Deprecated
public class HDFStorageProvider {

	private FileSystem hdfs = null;
	@Value("${hdfs.url}")
	private String hdfsUrl;

	// @PostConstruct
	public void start() {

		try {
			Configuration conf = new Configuration();
			conf.set("fs.defaultFS", hdfsUrl);
			hdfs = FileSystem.get(conf);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getHdfsUrl() {
		return hdfsUrl;
	}

	public void setHdfsUrl(String hdfsUrl) {
		this.hdfsUrl = hdfsUrl;
	}

	// @Override
	public void saveText(String path, String text)
			throws IllegalArgumentException, IOException {
		if (Strings.isNullOrEmpty(path))
			return;

		try (FSDataOutputStream out = hdfs.create(new Path(path))) {
			if (text != null)
				out.write(text.getBytes());
		}
	}

	// @Override
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

	// @Override
	public void save(String path, InputStream in) throws IOException {
		if (Strings.isNullOrEmpty(path) || in == null)
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

	// @Override
	public boolean exists(String path) throws IOException {
		if (Strings.isNullOrEmpty(path))
			return false;

		return hdfs.exists(new Path(path));
	}

	// @Override
	public int getFileCount(String path) throws FileNotFoundException,
			IOException {
		RemoteIterator<LocatedFileStatus> iterator = hdfs.listFiles(new Path(
				path), true);
		int count = 0;
		while (iterator.hasNext()) {
			count++;
			iterator.next();
		}

		return count;
	}

	// @Override
	public List<String> getFileRange(String path, Paging paging)
			throws FileNotFoundException, IOException {
		Set<LocatedFileStatus> statusSet = new TreeSet<>(new StatusComparator());
		RemoteIterator<LocatedFileStatus> iterator = hdfs.listFiles(new Path(
				path), true);
		while (iterator.hasNext()) {

			LocatedFileStatus status = iterator.next();
			statusSet.add(status);

		}

		List<String> ret = new ArrayList<>();
		Iterator<LocatedFileStatus> iterator2 = statusSet.iterator();
		int index = 1;
		while (iterator2.hasNext() && ret.size() < paging.getPageSize()) {
			LocatedFileStatus s = iterator2.next();
			if (index > (paging.getPage() - 1) * paging.getPageSize()
					&& index <= paging.getPage() * paging.getPageSize())
				ret.add(s.getPath().toString());
			index++;
		}

		return ret;
	}

	static class StatusComparator implements Comparator<LocatedFileStatus> {

		@Override
		public int compare(LocatedFileStatus o1, LocatedFileStatus o2) {
			if (o1.getModificationTime() == o2.getModificationTime())
				return 0;
			return o1.getModificationTime() > o2.getModificationTime() ? 1 : -1;
		}
	}
}
