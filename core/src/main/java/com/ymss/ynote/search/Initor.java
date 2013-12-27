package com.ymss.ynote.search;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

@Named
public class Initor implements ApplicationListener<ContextRefreshedEvent> {

	@Inject
	private List<Initable> initables;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		for (Initable init : initables) {
			init.init();
		}

	}

}
