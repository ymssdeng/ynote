package com.ymss.ynote.storage.provider;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.ymss.ynote.note.NotebookCategory;

public interface NotebookCategoryProvider {

	final String C_NOTEBOOKCATEGORY = "insert into category (name) values (#{0})";
	final String R_NOTEBOOKCATEGORY = "select * from category where id = #{0}";
	
	@Insert(C_NOTEBOOKCATEGORY)
	@Options(useGeneratedKeys = true, keyProperty = "id")
	void save(String name);
	
	@Select(R_NOTEBOOKCATEGORY)
	NotebookCategory getById(int id);
}
