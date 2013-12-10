package com.ymss.ynote.search;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;

import com.ymss.ynote.storage.INotebookStorage;

@Named
public class IndexInitor implements Initable {

	private IndexWriter iw;
	private List<Document> docs = new ArrayList<>();
	@Inject
	private INotebookStorage notebook;
	
	@Override
	public void init() {

		//createDocuments();
	}

}
