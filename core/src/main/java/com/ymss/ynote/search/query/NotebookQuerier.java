package com.ymss.ynote.search.query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.util.Version;
import org.springframework.context.annotation.DependsOn;

import com.ymss.ynote.note.Notebook;
import com.ymss.ynote.note.Storagable;
import com.ymss.ynote.storage.NotebookStorage;

@Named
@DependsOn("query")
public class NotebookQuerier extends Querier {

	@Named("nbqb")
	@Inject
	private QueryBuilder qbz;
	
	@Named("nbqrb")
	@Inject
	private QueryResultBuilder qrbz;
	
	public NotebookQuerier(){
	}
	
	@Override
	protected List<String> getFileds() {
		return Arrays.asList("name", "category");
	}

	@Named("nbqb")
	static class NotebookQueryBuilder implements QueryBuilder {

		@Override
		public Query fQuery(String f, String s) {
			QueryParser parser = new QueryParser(Version.LUCENE_42,
					f, new StandardAnalyzer(Version.LUCENE_42));
			Query query = null;
			try {
				query = parser.parse(s);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return query;
		}

		@Override
		public Query adlQuery() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Query advQuery() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	@Named("nbqrb")
	static class NotebookQueryResultBuilder implements QueryResultBuilder {

		@Inject
		private NotebookStorage nbs;
		private List<Document> hits;
		
		@SuppressWarnings("unchecked")
		@Override
		public <T extends Storagable> List<T> result() {
			List<T> lst = new ArrayList<>();
			for (Document doc : hits) {
				int id = doc.getField("id").numericValue().intValue();
				try {
					lst.add((T) nbs.getById(id));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return lst;
		}

		@Override
		public void setHits(List<Document> hits){
			this.hits = hits;
		}

		
	}

	@Override
	protected boolean hasAdlQuery() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void initBuilder() {
		super.qb = qbz;
		super.qrb = qrbz;
	}
}