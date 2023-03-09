package com.KoreaIT.java.AM;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		System.out.println("==프로그램 시작==");

		Scanner sc = new Scanner(System.in);
		int lastId = 0;
		List<Article> articles = new ArrayList<>();

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
			if (command.equals("article list")) {
				if (articles.size() == 0) {
					System.out.println("게시글이 없습니다.");
				}

				else {
					for (int i = articles.size() - 1; i >= 0; i--) {
						Article article = articles.get(i);
						System.out.println("번호" + "  //  " + "제목");
						System.out.println(article.id + "    //  " + article.title);
					}
				}

			}

			else if (command.startsWith("article detail ")) {
				String[] cmdDiv = command.split(" ");
				if (cmdDiv.length < 3) {
					System.out.println("명령어를 확인해주세요");
					continue;
				}
				int id = Integer.parseInt(cmdDiv[2]);

				Article foundArticle = null;
				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);

					if (article.id == id) {
						foundArticle = article;
						break;

					}
				}
				if (foundArticle == null) {
					System.out.println(id + "번 게시물은 존재하지 않습니다.");
					continue;
				}

				System.out.println("번호 : " + foundArticle.id);
				System.out.println("날짜:" + foundArticle.regDate);
				System.out.println("제목:" + foundArticle.title);
				System.out.println("내용:" + foundArticle.body);

			}

			else if (command.startsWith("article delete ")) {
				String[] cmdDiv = command.split(" ");
				if (cmdDiv.length < 3) {
					System.out.println("명령어를 확인해주세요");
					continue;
				}
				int id = Integer.parseInt(cmdDiv[2]);

				int foundIndex = -1;
				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);

					if (article.id == id) {

						foundIndex = i;
						break;

					}
				}
				if (foundIndex == -1) {
					System.out.println(id + "번 게시물은 존재하지 않습니다.");
					continue;
				}
				articles.remove(foundIndex);
				System.out.println(id + "번 게시물이 삭제되었습니다.");
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
				Article article = new Article(id, regDate, title, body);
				articles.add(article);
			}

			else {
				System.out.println("존재하지 않는 명령어입니다.");

			}

		}

		System.out.println("==프로그램 끝==");

		sc.close();
	}
}

class Article {

	int id;
	String title;
	String body;
	String regDate;
	String updateDate;

	public Article(int id, String regDate, String title, String body) {
		this.id = id;
		this.regDate = regDate;
		this.title = title;
		this.body = body;
	}

}
