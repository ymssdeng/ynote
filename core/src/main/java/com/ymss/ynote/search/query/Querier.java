package com.ymss.ynote.search.query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.ymss.ynote.search.index.IndexOperator;

public abstract class Querier {

	@Inject
	protected QueryOperator qo;
	@Inject
	protected QueryResultProcessor qrp;
	protected List<Document> docs;
	@Inject
	private IndexOperator io;
	private IndexSearcher is;

	@PostConstruct
	private void init() {
		is = new IndexSearcher(io.getIr());
	}

	public void search(String statement) {
		List<ScoreDoc> sds = new ArrayList<>();

		for (String f : getFileds()) {
			ScoreDoc[] sd = qo.search(statement, f);
			sds.addAll(Arrays.asList(sd));
		}

		Collections.sort(sds, new Comparator<ScoreDoc>() {

			@Override
			public int compare(ScoreDoc o1, ScoreDoc o2) {
				if (o1.score == o2.score)
					return 0;
				return o1.score > o2.score ? 1 : -1;
			}

		});

		Collections2.transform(sds, new Function<ScoreDoc, Document>() {

			@Override
			public Document apply(ScoreDoc input) {
				try {
					return is.doc(input.doc);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}

		});
	}

	protected abstract List<String> getFileds();

	public abstract Object result();
}