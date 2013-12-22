package com.ymss.ynote.storage.provider;

import org.apache.ibatis.annotations.Insert;

import com.ymss.ynote.note.Notebook;

public interface NotebookStorageProvider {

	@Insert("insert into notebook (name, category_id) values (#{name}, #{category.id})")
	void save(Notebook notebook);
}
