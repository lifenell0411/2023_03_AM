package com.KoreaIT.java.AM.Dao;

import java.util.ArrayList;
import java.util.List;

import com.KoreaIT.java.AM.dto.Article;

public class ArticleDao extends Dao {
	public List<Article> articles;

	public int getLastId() {
		return lastId;
	}

	public ArticleDao() {
		articles = new ArrayList<>();
	}

	public void add(Article article) {
		articles.add(article);
		lastId++;
	}

	public int setNewId() {

		return lastId + 1;
	}
}