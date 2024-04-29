package com.back.admin.dao;

import java.util.List;

import com.back.admin.dto.AdminMemberDTO;

public interface AdminMemberDAO {

	List<AdminMemberDTO> list(int start);

	List<AdminMemberDTO> allList();

	List<AdminMemberDTO> listFilterState(int start, String state);

	int allStateCount();

	int filteringStateCount(String state);

	List<AdminMemberDTO> idSearchList(String memberSearchWord, int start);

	Object searchMemberIdCount(String memberSearchWord);

	AdminMemberDTO detailLoad(String memberId);

	AdminMemberDTO memberDetail(String memberId);

	void writeUpdate(String memberName, String memberPass, String memberLevel, String memberAddress,
			String memberGender, String memberPosition, String memberState, String memberId);


}