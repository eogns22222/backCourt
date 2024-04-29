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


}