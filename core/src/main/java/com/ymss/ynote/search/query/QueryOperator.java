package com.ymss.ynote.search.query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.util.Version;
import org.springframework.context.annotation.DependsOn;

import com.ymss.ynote.search.Initable;
import com.ymss.ynote.search.index.IndexOperator;

@Named
@DependsOn("index")
public class QueryOperator implements Initable {

	private final Logger logger = Logger.getLogger(QueryOperator.class);
	@Inject
	private IndexOperator io;
	private IndexSearcher is;

	@Override
	public void init() {
		is = new IndexSearcher(io.getIr());
	}

	public Map<String, List<Document>> search(String statement,
			List<String> fields) {
		Map<String, List<Document>> map = new HashMap<String, List<Document>>();

		try {

			for (String f : fields) {
				QueryParser parser = new QueryParser(Version.LUCENE_42, f,
						new StandardAnalyzer(Version.LUCENE_42));
				Query query = parser.parse(statement);
				logger.debug(query.toString());

				TopDocs topDocs = is.search(query, 10);

				List<Document> hits = new ArrayList<>();
				for (ScoreDoc sc : topDocs.scoreDocs) {
					hits.add(is.doc(sc.doc));
				}

				map.put(f, hits);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return map;
	}
}
