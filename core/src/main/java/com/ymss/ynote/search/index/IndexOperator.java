package com.ymss.ynote.search.index;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

import com.ymss.ynote.note.Notebook;
import com.ymss.ynote.search.Initable;
import com.ymss.ynote.storage.Paging;
import com.ymss.ynote.storage.Storage;

@Named("index")
public class IndexOperator implements Initable {

	private final Logger logger = Logger.getLogger(IndexOperator.class);
	// used by query
	private IndexReader ir;
	private Directory dir;

	private IndexWriter iw;
	private List<Document> docs = new ArrayList<>();
	@Inject
	private Storage<Notebook> nbStorage;

	@Override
	public void init() {

		try {
			// create dir
			dir = new RAMDirectory();
			IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_42,
					new StandardAnalyzer(Version.LUCENE_42));
			iw = new IndexWriter(dir, config);

			// create document
			Paging paging = new Paging();
			while (nbStorage.hasNextPage(paging)) {
				for (Notebook book : nbStorage.getPage(paging)) {
					Document doc = new Document();
					doc.add(new IntField("id", book.getId(), Store.YES));
					doc.add(new TextField("name", book.getName(), Store.YES));
					doc.add(new StringField("category", book.getCategory()
							.toString(), Store.YES));
					docs.add(doc);
				}
				paging.setPage(paging.getPage() + 1);
			}

			// write index
			for (Document doc : docs) {
				iw.addDocument(doc);
			}

			// commit index
			iw.commit();

			// get near real time reader
			ir = DirectoryReader.open(iw, true);

			// clear
			docs.clear();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public IndexReader getIr() {
		return ir;
	}

	public Directory getDir() {
		return dir;
	}

}
