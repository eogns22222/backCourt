package com.back.member.dao;

import java.util.Map;

import com.back.mypage.dto.MypageDTO;

public interface MemberDAO {

	String loginId(String id, String pw);

	int Join(Map<String, String> param);

	int overlay(String id);
	
	// 고객, 관리자 구별
	String loginperm(String id, String pw);

}
