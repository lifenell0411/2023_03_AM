package com.KoreaIT.java.AM.dto;

public class Article {

	public int id;
	public String title;
	public String body;
	public String regDate;
	public String updateDate;
	public int hitCount;

	public Article(int id, String regDate, String updateDate, String title, String body, int hitCount) {

		this.id = id;
		this.regDate = regDate;
		this.title = title;
		this.body = body;
		this.updateDate = updateDate;
	}

	public Article(int id, String regDate, String updateDate, String title, String body) {
		this(id, regDate, updateDate, title, body, 0);
	}
}
