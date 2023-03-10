package com.KoreaIT.java.AM.Dao;

import java.util.ArrayList;
import java.util.List;

import com.KoreaIT.java.AM.dto.Article;
import com.KoreaIT.java.AM.dto.Member;

public class MemberDao extends Dao {
	public List<Member> members;

	public int getLastId() {
		return lastId;
	}

	public MemberDao() {
		members = new ArrayList<>();
	}

	public void add(Member member) {
		members.add(member);
		lastId++;
	}

}