package com.ymss.ynote.storage;

public interface Storageable<T> {

	String toJSON();

	T fromJSON(String text);
}
