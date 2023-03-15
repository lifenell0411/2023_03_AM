package com.KoreaIT.java.AM.container;

import com.KoreaIT.java.AM.Dao.ArticleDao;
import com.KoreaIT.java.AM.Dao.MemberDao;
import com.KoreaIT.java.service.ArticleService;
import com.KoreaIT.java.service.MemberService;

public class Container {
	public static ArticleDao articleDao;
	public static MemberDao memberDao;

	public static ArticleService articleService;
	public static MemberService memberService;

	static {
		articleDao = new ArticleDao();
		memberDao = new MemberDao();

		articleService = new ArticleService();
		memberService = new MemberService();
	}
}