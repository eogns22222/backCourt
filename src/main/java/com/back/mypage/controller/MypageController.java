package com.back.mypage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.back.mypage.dto.MypageDTO;
import com.back.mypage.service.MypageService;

@Controller
public class MypageController {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired MypageService mypageService;
	
	// ============== 내 포인트 리스트 ===================
	
	
		// 내 포인트 리스트 이동 (일반 리스트 타입)
				@RequestMapping(value = "/mypage/point.go")
				public String pointGo(Model model,HttpSession session) {
					logger.info("내 포인트 리스트 접속");
					String page = "redirect:/login";
					
					//로그인 회원 아이디 가져오기
					String loginId =  (String) session.getAttribute("loginId");
					
					if (loginId!=null) {
						logger.info("로그인 아이디 : "+loginId);
						
						
						
						page = "/mypage/point";
						
					}
					
					return page;
				}
				
				//포인트 리스트 출력(아작스 버전)
				//비동기 방식 : 일단 페이지에 토착한다음
				@RequestMapping(value = "/mypage/point.ajax",method = RequestMethod.POST)
				@ResponseBody //response 객체로 ㅂㄴ환 하는 데........
				//아자그 하고 그나마 비슷한거
				public Map<String, Object> listCall(HttpSession session,String page,String cnt){
					logger.info("페이지당 보여줄 갯수 : "+cnt);
					logger.info("요청 페이지 :"+page);
					
					int currPage = Integer.parseInt(page); //문자를 숫자로 변환
					int pageParCnt = Integer.parseInt(cnt);
					
					String loginId = (String) session.getAttribute("loginId");
					logger.info("아잨스 에서 세션 아이디 : "+loginId);
					Map<String, Object> map = mypageService.point_list_ajax(loginId,currPage,pageParCnt);
					
					return map;// 여기에서 처리된 데이처를 리턴 해준다.
				}
				
				//내 지갑 사정 (아작스)
				@RequestMapping(value = "/mypage/my_allpoint.ajax")
				@ResponseBody
				public Map<String, Object> my_allpoint_ajax(HttpSession session){
					logger.info("내 지갑 사정(아작스) ");
					
					String loginId = (String) session.getAttribute("loginId");
					logger.info("아잨스 에서 세션 아이디 : "+loginId);
					
					
					Map<String, Object> map = mypageService.my_allpoint_ajax(loginId);
					
					return map;
				}
				
				
				
				//포인트 충전 페이지 이동
				@RequestMapping(value = "/mypage/point_add.go")
				public String ponitAdd(Model model,HttpSession session) {
					logger.info("포인트 충전 페이지 이동");
					String page = "redirect:/login";
					
					//로그인 회원 아이디 가져오기
					String loginId = (String) session.getAttribute("loginId");
					logger.info("loginId"+loginId);
					
					if (loginId!=null) {
						
						page = "mypage/point_add";
						
					}
					
					
					return page;
				}
				
				//충전 하기
				@RequestMapping(value = "/mypage/point_add.do")
				public String ChargingDo(String Charging,HttpSession session) {
					logger.info("충전 처리중...");
					logger.info("충전 내역 : {}",Charging);
					
					//로그인 회원 아이디 가져오기
					String loginId = (String) session.getAttribute("loginId");
					logger.info("loginId"+loginId);

					
					mypageService.Charging_do(loginId,Charging);
					
					//정보 업데이트
					mypageService.point_update(loginId);
					return"redirect:/mypage/point.go";
				}
				
				//포인트 환급 페이지 이동
				@RequestMapping(value = "/mypage/point_minus.go")
				public String pointMins(Model model,HttpSession session) {
					logger.info("환급 페이지 이동");
					
					String page = "redirect:/login";
					//로그인 회원 아이디 가져오기
					String loginId = (String) session.getAttribute("loginId");
					logger.info("loginId"+loginId);
					
					if (loginId!=null) {
						
						
						page = "mypage/point_minus";
						
					}
					
					
					return page;
				}
				
				//환급 페이지 처리
				@RequestMapping(value = "/mypage/point_minus.do")
				public String PointMinus(String minus,HttpSession session,Model model) {
					logger.info("------------------  환급 처리 컨트롤러  ----------------------");
					logger.info("환급 중...");
					logger.info("환급 금액 : "+minus);
					
					String page = "mypage/point_minus";
					
					//로그인 회원 아이디 가져오기
					String loginId = (String) session.getAttribute("loginId");
					logger.info("로그인 중인 사람 : "+loginId);

					logger.info("로그인 중인 사람 : "+loginId);
					
					Map<String, Object> point = mypageService.my_allpoint_ajax(loginId);
					logger.info("내 지갑 포인트 : "+point.get("point"));
					int poin = Integer.parseInt((String) point.get("point"));
					int min = Integer.parseInt(minus);
					int sum = poin-min;
					
					if (sum>0) {
						mypageService.PointMinus(loginId,minus);
						
						//포인트 정보 업데이트
						mypageService.point_update(loginId);
						page = "redirect:/mypage/point.go";
					}else {
						page = "mypage/point_minus";
						model.addAttribute("sum",sum);
					}

					
					
					return page;
					//return null;
				}

		
		
		
		
		
		//============================================
		
		
		@RequestMapping(value = "/mypage/report")
		public String reportGo() {
			logger.info("제발");
			return "/mypage/report";
		}
		

		@RequestMapping(value ="/mypage/report.do", method = RequestMethod.POST)
		public String report(Model model, HttpSession session, String title, String contents){
			logger.info("report title :{}, contents : {}",title, contents);
			String page = "/mypage/report";
			// 받아온 아이디 getsession하기
			String id = "admin";
			// 전에 페이지에서 받아온 글구분이랑, 글번호 변경
			String report_write_type = "신고";
			int report_write_idx = 1;
			Map<String, Object> map = new HashMap<>();
			map.put("id", id);
			map.put("report_tit", title);
			map.put("report_content", contents);
			map.put("report_write_type", report_write_type);
			map.put("report_write_idx", report_write_idx);
			int row = mypageService.report(map);
			
			if(row >= 1) {
				page = "index";
			}
			
			return page;
		}
		
		@RequestMapping(value = "/mypage/jjim.go")
		public String jjimGo() {
			return "/mypage/jjim";
		}
		
		@RequestMapping(value = "/mypage/jjimList.ajax")
		@ResponseBody
		public Map<String, Object> jjimList(){
			logger.info("mypage/jjimList ");
			// session check 필요
			String id = "admin"; //id 받아오는 로직으로 수정 필요
			return mypageService.jjimList(id);
		}
		
		@RequestMapping(value = "/mypage/jjimDel.ajax")
		@ResponseBody
		public Map<String, Object> jjimDel(@RequestParam(value = "selectedIdxList[]") List<String> selectedIdxList){
			logger.info("{}",selectedIdxList);
			return mypageService.jjimDel(selectedIdxList);
		}



// ============ 신청/예약 리스트 ==================

// 신청/예약  리스트 이동
@RequestMapping(value = "/mypage/match_ask_list.go")
	public String matchAskListGo() {
	return "mypage/match_ask_list";
}

// 신청/예약 리스트(아작스)
@RequestMapping(value = "/mypage/match_ask_list.ajax")
@ResponseBody
	public Map<String, Object>  match_ask_list_ajax(String Choice,String page,String num,HttpSession session) {
	logger.info("아작스 접근");
	logger.info("선택 버튼 : {}",Choice);
	logger.info("n번 부터 : {}",page);
	logger.info("n개 :  {} ",num);

	//로그인 중인 아이디
	String loginId = (String) session.getAttribute("loginId");	
	logger.info("로그인 아이디 : {}",loginId);
	
	//값 변환
	int currPage = Integer.parseInt(page); //n번 부터
	int pageParnum = Integer.parseInt(num); //n개
	
	//아작스를 컨트롤러에서 처리 하지 않고 서비스로 보낸다.
	Map<String, Object> map = mypageService.match_ask_list_ajax(loginId,Choice,currPage,pageParnum);
	
	return map;
}


	//아작스 리스트 삭제 (공식 경기 삭제)
	@RequestMapping(value = "/mypage/official_match_list_del.ajax", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> match_ask_list_del(String idx,HttpSession session){
		logger.info("아작스 리스트 삭제 중...");
		logger.info("idx : {}",idx);
		
		//로그인 중인 아이디
		String loginId = (String) session.getAttribute("loginId");	
		logger.info("로그인 아이디 : {}",loginId);
		
		Map<String, Object> map = 	mypageService.match_ask_list_del(loginId,idx);
		
		return map;
	}


	//아작스 (게스트 리스트 삭제)
	@RequestMapping(value = "/mypage/guset_match_list_del.ajax")
	@ResponseBody
	public Map<String, Object> guset_match_list_del(String idx,HttpSession session){
		logger.info("아작스(게스트 리스트 삭제 접속)");
		
		//로그인 중인 아이디
		String loginId = (String) session.getAttribute("loginId");	
		logger.info("로그인 아이디 : {}",loginId);
		
		Map<String, Object> map = mypageService.guset_match_list_del(loginId,idx);
		
		return map;
	}

	
	//아작스 (구장 리스트 삭제)
	@RequestMapping(value = "/mypage/court_match_list_del")
	@ResponseBody
	public Map<String, Object> court_match_list_del(String idx,HttpSession session){
		logger.info("아작스 (구장 리스트 삭제) 접근");
		
		//로그인 중인 아이디
		String loginId = (String) session.getAttribute("loginId");	
		logger.info("로그인 아이디 : {}",loginId);
		
		Map<String, Object> map =mypageService.court_match_list_del(loginId,idx);
		
		
		return map;
	}
	


}