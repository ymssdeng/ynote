package com.ymss.ynote.search.query;

import java.util.Arrays;
import java.util.List;

import javax.inject.Named;

import org.apache.lucene.search.Query;
import org.springframework.context.annotation.DependsOn;

import com.ymss.ynote.note.Notebook;
import com.ymss.ynote.note.Storagable;

@Named
@DependsOn("query")
public class NotebookQuerier extends Querier {

	public NotebookQuerier(){
	}
	
	@Override
	protected List<String> getFileds() {
		return Arrays.asList("name", "category");
	}

	static class NotebookQueryBuilder implements QueryBuilder {

		@Override
		public Query fQuery(String f) {
			// TODO Auto-generated method stub
			return null;
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

	static class NotebookQueryResultBuilder implements QueryResultBuilder {

		@Override
		public <T extends Storagable> List<T> result() {
			return null;//qrp.process(docs, Notebook.class);
		}

		

		
	}

	@Override
	protected boolean hasAdlQuery() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void initBuilder(QueryBuilder qb, QueryResultBuilder qrb) {
		this.qb = new NotebookQueryBuilder();
		this.qrb = new NotebookQueryResultBuilder();
	}
}