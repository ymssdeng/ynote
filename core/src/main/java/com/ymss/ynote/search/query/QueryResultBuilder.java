package com.ymss.ynote.search.query;

import java.util.List;

import org.apache.lucene.document.Document;

import com.ymss.ynote.note.Storagable;

public interface QueryResultBuilder {

	<T extends Storagable> List<T> result();

	void setHits(List<Document> hits);
}
