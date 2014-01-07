package com.ymss.ynote.search.query;

import org.apache.lucene.search.Query;

public interface QueryBuilder {

	Query fQuery(String f, String s);
	
	Query adlQuery();
	
	Query advQuery();

}