package com.KoreaIT.java.AM.Dao;

import java.util.ArrayList;
import java.util.List;

import com.KoreaIT.java.AM.dto.Article;

public class ArticleDao {
	public List<Article> articles;

	public ArticleDao() {
		articles = new ArrayList<>();
	}
}