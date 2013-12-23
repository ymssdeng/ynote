package com.ymss.ynote.storage.provider;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.ymss.ynote.note.Notebook;

public interface NotebookStorageProvider {

	// equals to #{0.name}, #{param1.name}
	final String C_NOTEBOOK = "insert into notebook (name, category_id) values (#{name}, #{category.id})";
	final String R_NOTEBOOK = "select * from notebook where id = #{0}";

	@Insert(C_NOTEBOOK)
	// @SelectKey(before = false, keyProperty = "id", resultType =
	// Integer.class, statement = { "select last_insert_id()" })
	@Options(useGeneratedKeys = true, keyProperty = "id")
	void save(Notebook notebook);

	@Select(R_NOTEBOOK)
	@Results(value = {
			@Result(column = "created_date", property = "timestamp"),
			@Result(column = "category_id", property = "category", one = @One(select = "com.ymss.ynote.storage.provider.NotebookCategoryProvider.getById")) })
	Notebook getById(int id);

	@Select("select * from notebook where id > (#{0}-1)*#{1} order by id asc limit #{1}")
	List<Notebook> getPage(int page, int pageSize, boolean asc);
}
