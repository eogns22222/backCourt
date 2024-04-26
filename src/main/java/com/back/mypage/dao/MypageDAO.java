package com.back.mypage.dao;

import java.util.List;
import java.util.Map;

import com.back.mypage.dto.MypageDTO;
import com.back.official.dto.OfficialDTO;

public interface MypageDAO {
	
// =========== 내 포인트 리스트 =============
	
	String report(String id);

	//내 지갑 보기
	String my_allpoint_ajax(String loginId);

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
	
	
	List<OfficialDTO> official_match_list_ajax(String loginId, int pageParnum, int startPage);

	Object official_match_allConut(int pageParnum, String loginId);

	List<MypageDTO> guest_match_list_ajax(String loginId, int pageParnum, int startPage);

	Object guest_match_allConu(int pageParnum, String loginId);

	List<MypageDTO> court_match_list_ajax(String loginId, int pageParnum, int startPage);

	Object court_match_allConu(int pageParnum, String loginId);

	int match_ask_list_del(String loginId,String idx);

	int guset_match_list_del(String loginId, String idx);

	int court_match_list_del(String loginId, String idx);








}
