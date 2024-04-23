package com.back.mypage.dao;

import java.util.List;
import java.util.Map;

import com.back.mypage.dto.MypageDTO;

public interface MypageDAO {
	
// =========== 내 포인트 리스트 =============
	
	String report(String id);
	
	List<MypageDTO> point_list(String loginId);

	void Charging_do(String loginId, String charging);

	void PointMinus(String loginId, String minus);

	String point(String loginId);
	
	List<MypageDTO> point_list_ajax(String loginId, int currPage, int start);

	Object point_allConut(int pageParCnt, String loginId);
	
	void point_update(String loginId);
	
// ============================================

	int report(Map<String, Object> map);


	List<MypageDTO> jjimList(String id);

	int jjimListCount(String id);

	int jjimDel(int jjimidx);

	
	
// ========== 신청/예약 리스트 ====================
	
	
	List<MypageDTO> match_ask_list_ajax(String loginId, int pageParnum, int start);

	//Object match_allConut(int pageParnum, String loginId);



}
