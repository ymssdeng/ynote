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
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
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

	public Querier(){
		
	}
	
	protected QueryBuilder qb;
	protected QueryResultBuilder qrb;
	protected abstract void initBuilder(QueryBuilder qb, QueryResultBuilder qrb);
	
	@PostConstruct
	private void init() {
		is = new IndexSearcher(io.getIr());
	}

	public QueryResultBuilder search2(String s) {
		BooleanQuery bQuery  = new BooleanQuery();
		for (String f : getFileds()) {
			bQuery.add(qb.fQuery(f), BooleanClause.Occur.SHOULD);
		}
		
		if (hasAdlQuery())
			bQuery.add(qb.adlQuery(), BooleanClause.Occur.MUST);
		
		try {
			is.search(bQuery, 10);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// transform
		return qrb;
	}
	
	protected abstract boolean hasAdlQuery();

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

		docs = Lists.transform(sds, new Function<ScoreDoc, Document>() {

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
}