package com.ymss.ynote.search.query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.lucene.document.Document;

import com.ymss.ynote.note.Notebook;
import com.ymss.ynote.note.Storagable;
import com.ymss.ynote.storage.NotebookStorage;
import com.ymss.ynote.storage.Storage;

@Named
public class QueryResultProcessor {

	@Inject
	private NotebookStorage nbs;
	@SuppressWarnings("rawtypes")
	private Map<Class, Storage> map = new HashMap<>();
	
	@PostConstruct
	public void initMap() {
		map.put(Notebook.class, nbs);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Storagable> List<T> process(List<Document> hits, Class<T> clazz){
		List<T> lst = new ArrayList<>();
		
		try {
			for (Document doc : hits) {
				int id = doc.getField("id").numericValue().intValue();
				if (map.containsKey(clazz))
					lst.add((T) map.get(clazz).getById(id));
			}
		} catch (IOException e) {
			// TODO: handle exception
		}
		
		return lst;
		
	}
}