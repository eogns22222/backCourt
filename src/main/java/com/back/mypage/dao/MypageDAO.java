package com.back.mypage.dao;

import java.util.List;
import java.util.Map;

import com.back.mypage.dto.MypageDTO;

public interface MypageDAO {

	int report(Map<String, Object> map);

	List<MypageDTO> jjimList(String id);

	int jjimListCount(String id);

	int jjimDel(int jjimidx);
	
}
