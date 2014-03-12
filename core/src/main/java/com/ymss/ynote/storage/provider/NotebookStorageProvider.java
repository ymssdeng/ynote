package com.ymss.ynote.storage.provider;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.ymss.ynote.note.Notebook;

public interface NotebookStorageProvider {

	final String C = "insert into notebook (name, category_id) values (#{name}, #{category.id})";
	final String R_COUNT = "select count(*) from notebook where id > (#{0} - 1) * #{1}";

	@Insert(C)
	@Options(useGeneratedKeys = true, keyProperty = "id")
	void save(Notebook notebook);

	Notebook getById(int id);

	List<Notebook> getPage(int page, int pageSize, boolean asc);

	@Select(R_COUNT)
	int hasNextPage(int page, int pageSize);
}
