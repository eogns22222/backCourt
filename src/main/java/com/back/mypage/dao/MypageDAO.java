package com.back.mypage.dao;

import java.util.List;
import java.util.Map;

import com.back.mypage.dto.MypageDTO;

public interface MypageDAO {
	
	String report(String id);
	
	List<MypageDTO> point_list(String loginId);

	void ChargingDo(String loginId, String charging);

	void PointMinus(String loginId, String minus);

	String point(String loginId);
	
	List<MypageDTO> point_list_ajax(String loginId);

// ============================================

	int report(Map<String, Object> map);


	List<MypageDTO> jjimList(String id);

	int jjimListCount(String id);

	int jjimDel(int jjimidx);

}
