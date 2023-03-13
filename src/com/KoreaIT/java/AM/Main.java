package com.KoreaIT.java.AM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	static List<Article> articles = new ArrayList<>();
	static List<Member> members = new ArrayList<>();

	public static void main(String[] args) {
		System.out.println("==프로그램 시작==");

		Scanner sc = new Scanner(System.in);
		int lastId = 3;
		int lastMemberId = 0;
		makeTestData();
		while (true) {

			System.out.print("명령어 > ");
			String command = sc.nextLine().trim();

			if (command.length() == 0) {
				System.out.println("명령어를 입력해주세요");
				continue;
			}

			if (command.equals("exit")) {
				break;
			}

			if (command.startsWith("article list")) {
				if (articles.size() == 0) {
					System.out.println("게시글이 없습니다.");
					continue;
				}
				String searchKeyword = command.substring("article list".length()).trim();
				List<Article> forPrintArticles = articles;
				if (searchKeyword.length() > 0) {
					forPrintArticles = new ArrayList<>();
					for (Article article : articles) {
						if (article.title.contains(searchKeyword)) {
							forPrintArticles.add(article);
						}
					}
					if (forPrintArticles.size() == 0) {
						System.out.println("검색결과가 없습니다.");
						continue;
					}
				}
				System.out.println("번호" + "  //  " + "제목" + "     //   " + "조회");
				for (int i = forPrintArticles.size() - 1; i >= 0; i--) {
					Article article = forPrintArticles.get(i);

					System.out.println(article.id + "    //  " + article.title + "   //   " + article.hitCount);
				}

			}

			else if (command.startsWith("article detail ")) {

				String[] cmdDiv = command.split(" ");
				if (cmdDiv.length < 3) {
					System.out.println("명령어를 확인해주세요");
					continue;
				}
				int id = Integer.parseInt(cmdDiv[2]);

				Article foundArticle = getArticleById(id);

				if (foundArticle == null) {
					System.out.println(id + "번 게시물은 존재하지 않습니다.");
					continue;
				}
				foundArticle.hitCount++;
				System.out.println("번호 : " + foundArticle.id);
				System.out.println("작성날짜:" + foundArticle.regDate);
				System.out.println("수정날짜:" + foundArticle.updateDate);
				System.out.println("제목:" + foundArticle.title);
				System.out.println("내용:" + foundArticle.body);
				System.out.println("조회수:" + foundArticle.hitCount);

			}

			else if (command.startsWith("article delete ")) {
				String[] cmdDiv = command.split(" ");
				if (cmdDiv.length < 3) {
					System.out.println("명령어를 확인해주세요");
					continue;
				}
				int id = Integer.parseInt(cmdDiv[2]);

				int foundIndex = getArticeIndexbyId(id);

				if (foundIndex == -1) {
					System.out.println(id + "번 게시물은 존재하지 않습니다.");
					continue;
				}
				articles.remove(foundIndex);
				System.out.println(id + "번 게시물이 삭제되었습니다.");
			}

			else if (command.startsWith("article modify ")) {

				String[] cmdDiv = command.split(" ");
				if (cmdDiv.length < 3) {
					System.out.println("명령어를 확인해주세요");
					continue;
				}
				int id = Integer.parseInt(cmdDiv[2]);

				Article foundArticle = getArticleById(id);

				if (foundArticle == null) {
					System.out.println(id + "번 게시물은 존재하지 않습니다.");
					continue;
				}

				System.out.print("제목 : ");
				String updateDate = Util.getNowDateTimeStr();
				String newtitle = sc.nextLine();
				System.out.print("내용 : ");
				String newbody = sc.nextLine();
				System.out.println(id + "번글이 수정되었습니다.");
				foundArticle.title = newtitle;
				foundArticle.body = newbody;
				foundArticle.updateDate = updateDate;
			}

			else if (command.equals("article write")) {
				int id = lastId + 1;
				System.out.print("제목 : ");
				String regDate = Util.getNowDateTimeStr();
				String title = sc.nextLine();
				System.out.print("내용 : ");
				String body = sc.nextLine();
				System.out.println(id + "번글이 생성되었습니다.");
				lastId++;
				Article article = new Article(id, regDate, regDate, title, body);
				articles.add(article);

			} else if (command.equals("member join")) {
				int id = lastMemberId + 1;
				String regDate = Util.getNowDateTimeStr();
				String loginId = null;
				while (true) {
					System.out.print("로그인 아이디 : ");
					loginId = sc.nextLine();

					if (isJoinableLoginId(loginId) == false) {
						System.out.println("이미 사용중인 아이디입니다");
						continue;
					}

					break;

				}
				String loginPw = null;
				String loginPwConfirm = null;

				while (true) {
					System.out.print("로그인 비밀번호 : ");
					loginPw = sc.nextLine();
					System.out.print("로그인 비밀번호 확인: ");
					loginPwConfirm = sc.nextLine();

					if (loginPw.equals(loginPwConfirm) == false) {
						System.out.println("비밀번호를 확인해주세요");
						continue;
					}
					break;
				}

				System.out.print("이름 : ");
				String name = sc.nextLine();

				Member member = new Member(id, regDate, regDate, loginId, loginPw, name);
				members.add(member);

				System.out.printf("%d번 회원이 가입되었습니다\n", id);
				lastMemberId++;

			}

			else {
				System.out.println("존재하지 않는 명령어입니다.");

			}

		}

		System.out.println("==프로그램 끝==");

		sc.close();

	}

	private static boolean isJoinableLoginId(String loginId) {
		int index = getMemberIndexById(loginId);
		if (index == -1) {
			return true;
		}
		return false;
	}

	private static int getMemberIndexById(String loginId) {
		int i = 0;
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return i;
			}
			i++;
		}
		return -1;
	}

	public static int getArticeIndexbyId(int id) {
		int i = 0;
		for (Article article : articles) {
			if (article.id == id) {
				return i;

			}
			i++;
		}
		return -1;
	}

	public static Article getArticleById(int id) {
//		for (Article article : articles) {
//
//			if (article.id == id) {
//				return article;
//
//			}
//		}

		int index = getArticeIndexbyId(id);
		if (index != -1) {
			return articles.get(index);
		}
		return null;
	}

	public static void makeTestData() {

		System.out.println("테스트를 위한 데이터를 생성합니다.");
		articles.add(new Article(1, Util.getNowDateTimeStr(), Util.getNowDateTimeStr(), "제목1", "내용1", 11));
		articles.add(new Article(2, Util.getNowDateTimeStr(), Util.getNowDateTimeStr(), "제목2", "내용2", 22));
		articles.add(new Article(3, Util.getNowDateTimeStr(), Util.getNowDateTimeStr(), "제목3", "내용3", 33));
	}

	static class Article {

		int id;
		String title;
		String body;
		String regDate;
		String updateDate;
		int hitCount;

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
}

class Member {
	int id;
	String regDate;
	String updateDate;
	String loginId;
	String loginPw;
	String name;

	Member(int id, String regDate, String updateDate, String loginId, String loginPw, String name) {
		this.id = id;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.loginId = loginId;
		this.loginPw = loginPw;
		this.name = name;
	}
}
