package com.rkylin.wallet.query;

import java.io.Serializable;

public abstract class Query<T> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private T query;

	public T getQuery() {
		return query;
	}

	public void setQuery(T query) {
		this.query = query;
	}
}
