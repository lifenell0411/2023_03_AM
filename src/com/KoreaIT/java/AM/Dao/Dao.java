package com.KoreaIT.java.AM.Dao;

public abstract class Dao {
	protected int lastId;
	
	public Dao(){
		lastId= 0;
	}
	public abstract int getLastId();

}
