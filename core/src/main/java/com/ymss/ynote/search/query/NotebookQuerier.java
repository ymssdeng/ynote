package com.ymss.ynote.search.query;

import java.util.Arrays;
import java.util.List;

import javax.inject.Named;

import org.springframework.context.annotation.DependsOn;

import com.ymss.ynote.note.Notebook;

@Named
@DependsOn("query")
public class NotebookQuerier extends Querier {

	@Override
	protected List<String> getFileds() {
		return Arrays.asList("name", "category");
	}

	@Override
	public List<Notebook> result() {
		return qrp.process(docs, Notebook.class);
	}

}
