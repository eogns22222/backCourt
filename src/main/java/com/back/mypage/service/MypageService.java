package com.back.mypage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.mypage.dao.MypageDAO;
import com.back.mypage.dto.MypageDTO;

@Service
public class MypageService {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired MypageDAO mypageDAO;
	
	
	
	public String report(String id) {
		return mypageDAO.report(id);
	}
	public List<MypageDTO> point_list(String loginId) {
		
		return mypageDAO.point_list(loginId);
	}
	public void ChargingDo(String loginId, String charging) {
		mypageDAO.ChargingDo(loginId,charging);
	}
	public void PointMinus(String loginId, String minus) {
		mypageDAO.PointMinus(loginId,minus);
	}
	public String point(String loginId) {
		return mypageDAO.point(loginId);
	}
	
	public List<MypageDTO> point_list_ajax(String loginId) {
		return mypageDAO.point_list_ajax(loginId);
	}
	
	
	
	// ======================================
	
	public int report(Map<String, Object> map) {
		logger.info("report-service  map={}",map);
		return mypageDAO.report(map);
		
	}

	public Map<String, Object> jjimList(String id) {
		logger.info("mypage/jjimList Service ");
		Map<String, Object> map = new HashMap<String, Object>();
		List<MypageDTO> list = mypageDAO.jjimList(id);
		map.put("totalPage", mypageDAO.jjimListCount(id));
		map.put("list", list);
		return map;
	}

	public Map<String, Object> jjimDel(List<String> selectedIdxList) {
		int delCount = 0;
		for (String idx : selectedIdxList) {
			int jjimidx = Integer.parseInt(idx);
			logger.info(""+jjimidx);
			delCount += mypageDAO.jjimDel(jjimidx);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalDel", delCount);
		return map;
	}
	
}
