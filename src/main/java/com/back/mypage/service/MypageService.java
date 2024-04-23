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
	
// ========= 내 포인트 리스트 ===========
	
	public String report(String id) {
		return mypageDAO.report(id);
	}
	public List<MypageDTO> point_list(String loginId) {
		
		return mypageDAO.point_list(loginId);
	}
	public void Charging_do(String loginId, String charging) {
		mypageDAO.Charging_do(loginId,charging);
	}
	public void PointMinus(String loginId, String minus) {
		mypageDAO.PointMinus(loginId,minus);
	}
	public String point(String loginId) {
		return mypageDAO.point(loginId);
	}
	
	//아작스로 내 포인트 리스트 출력
	public Map<String, Object> point_list_ajax(String loginId, int currPage, int pageParCnt) {
	
		int start = (currPage-1)*pageParCnt;
		
		Map<String, Object> result = new HashMap<String, Object>(); //아작스로 데이터를 보내기 위한 작업
		List<MypageDTO> list = mypageDAO.point_list_ajax(loginId,pageParCnt,start); //아이디,n개 보여줘,n번부터
		logger.info("list Size : "+list.size());
		result.put("list", list); //맥에 리스트 라는 이름으로 리스트를 넣는다
		result.put("currPage",currPage); //n번부터
		result.put("totalPages", mypageDAO.point_allConut(pageParCnt,loginId)); //총 페이 갯수 계산
		
		
		
		return result;
	}
	
	public void point_update(String loginId) {
		mypageDAO.point_update(loginId);
		
	}
	
	// ================================
	
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
	
// ============= 신청/예약 리스트 ==============
	
	
	public Map<String, Object> match_ask_list_ajax(String loginId, String choice, int currPage, int pageParnum) {
		logger.info("로그인 아이디 : {}",loginId);
		logger.info("선택 버튼 : {}",choice);
		logger.info("n번 부터 : {}",currPage);
		logger.info("n개 :  {} ",pageParnum);
		
		int start = (currPage-1)*pageParnum;
		
		Map<String, Object> retul = new HashMap<String, Object>();

			List<MypageDTO> list = mypageDAO.match_ask_list_ajax(loginId,pageParnum,start);
			logger.info("list Size : {}",list.size());
			retul.put("list", list);
			retul.put("currPage",currPage);
			//retul.put("totalPages",mypageDAO.match_allConut(pageParnum,loginId));
			
			
		
		return retul;
	}
}
