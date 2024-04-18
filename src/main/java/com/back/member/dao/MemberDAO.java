package com.back.member.dao;

import java.util.Map;

public interface MemberDAO {

	String loginId(String id, String pw);

	int Join(Map<String, String> param);

	int overlay(String id);

}
