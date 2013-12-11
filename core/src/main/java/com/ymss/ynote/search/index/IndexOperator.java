package com.ymss.ynote.search.index;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

import com.ymss.ynote.note.Notebook;
import com.ymss.ynote.search.Initable;
import com.ymss.ynote.storage.INotebookStorage;

@Named
public class IndexOperator implements Initable {

	private IndexWriter iw;
	private List<Document> docs = new ArrayList<>();
	@Inject
	private INotebookStorage notebook;

	@Override
	public void init() {

		try {
			// create dir
			Directory dir = new RAMDirectory();
			IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_42,
					new StandardAnalyzer(Version.LUCENE_42));
			iw = new IndexWriter(dir, config);

			// create document
			for (Notebook book : notebook.get()) {
				Document doc = new Document();
				doc.add(new StringField("id", book.getId(), Store.YES));
				doc.add(new TextField("name", book.getName(), Store.YES));
				doc.add(new StringField("category", book.getCategory()
						.toString(), Store.YES));
				docs.add(doc);
			}

			// write index
			for (Document doc : docs) {
				iw.addDocument(doc);
			}

			// commit index
			iw.commit();
			docs.clear();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
