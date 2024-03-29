package com.KoreaIT.java.AM.Dao;

import java.util.ArrayList;
import java.util.List;

import com.KoreaIT.java.AM.dto.Article;

public class ArticleDao extends Dao {
	public List<Article> articles;

	public ArticleDao() {
		articles = new ArrayList<>();
	}

	public void add(Article article) {
		articles.add(article);
		lastId++;
	}

	public void remove(Article foundArticle) {
		articles.remove(foundArticle);
	}

	public int getLastId() {
		return lastId;
	}

	public int setNewId() {
		return lastId + 1;
	}

	public int getArticleIndexById(int id) {
		int i = 0;
		for (Article article : articles) {
			if (article.id == id) {
				return i;
			}
			i++;
		}
		return -1;
	}

	public Article getArticleById(int id) {
		int index = getArticleIndexById(id);

		if (index != -1) {
			return articles.get(index);
		}

		return null;
	}

	public List<Article> getArticles(String searchKeyword) {
		if (searchKeyword.length() != 0 && searchKeyword != null) {
			System.out.println("searchKeyword : " + searchKeyword);

			List<Article> forPrintArticles = new ArrayList<>();

			if (searchKeyword.length() > 0) {
				for (Article article : articles) {
					if (article.title.contains(searchKeyword)) {
						forPrintArticles.add(article);
					}
				}
			}

			return forPrintArticles;
		}
		return articles;
	}

}