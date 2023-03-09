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
		LocalDateTime now = LocalDateTime.now();
		String formatNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
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
				int id = Integer.parseInt(cmdDiv[2]);
				boolean found = false;

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);

					if (article.id == id) {
						found = true;
						System.out.println("번호 : " + article.id);
						System.out.println("날짜:" + formatNow);
						System.out.println("제목:" + article.title);
						System.out.println("내용:" + article.body);
						break;

					}
				}
				if (found == false) {
					System.out.println(id + "번 게시물은 존재하지 않습니다.");
				}

			}

			else if (command.startsWith("article delete ")) {
				String[] cmdDiv = command.split(" ");
				int id = Integer.parseInt(cmdDiv[2]);
				boolean found = false;

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					if (article.id == id) {
						found = true;
						articles.remove(i);
						System.out.println(id + "번 게시물이 삭제되었습니다.");
						break;
					}

					if (found == false) {
						System.out.println(id + "번 게시물은 존재하지 않습니다.");
					}

				}
			}

			else if (command.equals("article write")) {
				int id = lastId + 1;
				System.out.print("제목 : ");
				String title = sc.nextLine();
				System.out.print("내용 : ");
				String body = sc.nextLine();
				System.out.println(id + "번글이 생성되었습니다.");
				lastId++;
				Article article = new Article(id, title, body);
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

	public Article(int id, String title, String body) {
		this.id = id;
		this.title = title;
		this.body = body;
	}

}
