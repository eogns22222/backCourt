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
						//내가 임의로 넣은 값
						//String loginId = "a";
						logger.info("로그인 아이디 : "+loginId);
						
						//포인트 합 출력
						String point = mypageService.point(loginId);
						model.addAttribute("point",point);
						page = "/mypage/point";
						
						//리스트 출력
						List<MypageDTO> list = mypageService.point_list(loginId);
						model.addAttribute("pont_list",list);
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
					//String loginId = "a";
					logger.info("아잨스 에서 세션 아이디 : "+loginId);
					Map<String, Object> map = mypageService.point_list_ajax(loginId,currPage,pageParCnt);
					
					return map;// 여기에서 처리된 데이처를 리턴 해준다.
				}
				
				
				//포인트 충전 페이지 이동
				@RequestMapping(value = "/mypage/point_add.go")
				public String ponitAdd(Model model,HttpSession session) {
					logger.info("포인트 충전 페이지 이동");
					String page = "redirect:/login";
					
					//로그인 회원 아이디 가져오기
					String loginId = (String) session.getAttribute("loginId");
					//logger.info("loginId"+loginId);
					
					if (loginId!=null) {
						//내가 임의로 넣은 값
						//String loginId = "a";
						
						String point = mypageService.point(loginId);
						model.addAttribute("point",point);
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
					//logger.info("loginId"+loginId);
					
					//내가 임의로 넣은 값
					//String loginId = "a";
					
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
					//logger.info("loginId"+loginId);
					
					if (loginId!=null) {
						//내가 임의로 넣은 값
						//String loginId = "a";
						
						String point = mypageService.point(loginId);
						model.addAttribute("point",point);
						
						page = "mypage/point_minus";
						
					}
					
					
					return page;
				}
				
				//환급 페이지 처리
				@RequestMapping(value = "/mypage/point_minus.do")
				public String PointMinus(String minus,HttpSession session) {
					logger.info("환급 중...");
					logger.info("환급 금액 : "+minus);
					
					//로그인 회원 아이디 가져오기
					String loginId = (String) session.getAttribute("loginId");
					//logger.info("로그인 중인 사람 : "+loginId);
					
					//내가 임의로 넣은 값
					//String loginId = "a";
					logger.info("로그인 중인 사람 : "+loginId);
					
					mypageService.PointMinus(loginId,minus);
					
					//포인트 정보 업데이트
					mypageService.point_update(loginId);
					
					return "redirect:/mypage/point.go";
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
	}