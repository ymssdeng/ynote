package com.ymss.ynote.search.query;

import org.apache.lucene.search.Query;

public interface QueryBuilder {

	Query fQuery(String f);
	
	Query adlQuery();
	
	Query advQuery();

}