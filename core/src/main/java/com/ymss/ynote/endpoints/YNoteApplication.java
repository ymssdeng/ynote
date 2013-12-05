package com.ymss.ynote.endpoints;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/")
public class YNoteApplication extends Application {
	   HashSet<Object> singletons = new HashSet<Object>();

	    public YNoteApplication()
	    {
	        singletons.add(new NoteEndpoints());
	    }

	    @Override
	    public Set<Class<?>> getClasses()
	    {
	        HashSet<Class<?>> set = new HashSet<Class<?>>();
	        return set;
	    }

	    @Override
	    public Set<Object> getSingletons()
	    {
	        return singletons;  
	    }
}