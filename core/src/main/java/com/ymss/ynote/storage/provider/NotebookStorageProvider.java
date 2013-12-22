package com.ymss.ynote.storage.provider;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectKey;

import com.ymss.ynote.note.Notebook;

public interface NotebookStorageProvider {

	@Insert("insert into notebook (name, category_id) values (#{name}, #{category.id})") // equals to #{0.name}, #{param1.name}
	@SelectKey(before = false, keyProperty = "id", resultType = Integer.class, statement = { "select last_insert_id()" })
	void save(Notebook notebook);
}
