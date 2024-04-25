package com.back.mypage.dto;

import java.sql.Date;

public class MypageDTO {
	
	private int report_write_idx;
	private String report_write_type;
	private String report_content;
	private String id;
	private String report_tit;
	
	//jjim 
	private int jjimIdx;
	private String courtName;
	private String courtAddress;
	private String courtPrice;
	
	public int getReport_write_idx() {
		return report_write_idx;
	}
	public void setReport_write_idx(int report_write_idx) {
		this.report_write_idx = report_write_idx;
	}
	public String getReport_write_type() {
		return report_write_type;
	}
	public void setReport_write_type(String report_write_type) {
		this.report_write_type = report_write_type;
	}
	public String getReport_content() {
		return report_content;
	}
	public void setReport_content(String report_content) {
		this.report_content = report_content;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getReport_tit() {
		return report_tit;
	}
	public void setReport_tit(String report_tit) {
		this.report_tit = report_tit;
	}
	

	//jjim
	public int getJjimIdx() {
		return jjimIdx;
	}
	public void setJjimIdx(int jjimIdx) {
		this.jjimIdx = jjimIdx;
	}
	public String getCourtName() {
		return courtName;
	}
	public void setCourtName(String courtName) {
		this.courtName = courtName;
	}
	public String getCourtAddress() {
		return courtAddress;
	}
	public void setCourtAddress(String courtAddress) {
		this.courtAddress = courtAddress;
	}
	public String getCourtPrice() {
		return courtPrice;
	}
	public void setCourtPrice(String courtPrice) {
		this.courtPrice = courtPrice;
	}

	// =====================================

	
		//포인트 관련된 DB
		
		private int point_idx;
		private Date point_date;
		private int point_change;
		private String point_state;
		private int write_idx;
		private String write_type;


		public int getPoint_idx() {
			return point_idx;
		}
		public void setPoint_idx(int point_idx) {
			this.point_idx = point_idx;
		}
		public Date getPoint_date() {
			return point_date;
		}
		public void setPoint_date(Date point_date) {
			this.point_date = point_date;
		}
		public int getPoint_change() {
			return point_change;
		}
		public void setPoint_change(int point_change) {
			this.point_change = point_change;
		}
		public String getPoint_state() {
			return point_state;
		}
		public void setPoint_state(String point_state) {
			this.point_state = point_state;
		}
		public int getWrite_idx() {
			return write_idx;
		}
		public void setWrite_idx(int write_idx) {
			this.write_idx = write_idx;
		}
		public String getWrite_type() {
			return write_type;
		}
		public void setWrite_type(String write_type) {
			this.write_type = write_type;
		}

		//======== 게스트 모집 신청리스트 =========
		
		private int guest_join_idx;
		private int guest_idx;
		private String guest_join_state;
		private Date guest_date;

		public int getGuest_join_idx() {
			return guest_join_idx;
		}
		public void setGuest_join_idx(int guest_join_idx) {
			this.guest_join_idx = guest_join_idx;
		}
		public int getGuest_idx() {
			return guest_idx;
		}
		public void setGuest_idx(int guest_idx) {
			this.guest_idx = guest_idx;
		}
		public String getGuest_join_state() {
			return guest_join_state;
		}
		public void setGuest_join_state(String guest_join_state) {
			this.guest_join_state = guest_join_state;
		}
		public Date getGuest_date() {
			return guest_date;
		}
		public void setGuest_date(Date guest_date) {
			this.guest_date = guest_date;
		}
		
	// ========== 게스트 모집 ============
		
		private String court_booking_idx;
		private int team_idx;
		private String guest_position;
		private String guest_level;
		private String guest_gender;
		private int guest_to;
		private String guest_info;
		private int guest_fee;
		private String guest_state;
		

		public String getCourt_booking_idx() {
			return court_booking_idx;
		}
		public void setCourt_booking_idx(String court_booking_idx) {
			this.court_booking_idx = court_booking_idx;
		}
		public int getTeam_idx() {
			return team_idx;
		}
		public void setTeam_idx(int team_idx) {
			this.team_idx = team_idx;
		}
		public String getGuest_position() {
			return guest_position;
		}
		public void setGuest_position(String guest_position) {
			this.guest_position = guest_position;
		}
		public String getGuest_level() {
			return guest_level;
		}
		public void setGuest_level(String guest_level) {
			this.guest_level = guest_level;
		}
		public String getGuest_gender() {
			return guest_gender;
		}
		public void setGuest_gender(String guest_gender) {
			this.guest_gender = guest_gender;
		}
		public int getGuest_to() {
			return guest_to;
		}
		public void setGuest_to(int guest_to) {
			this.guest_to = guest_to;
		}
		public String getGuest_info() {
			return guest_info;
		}
		public void setGuest_info(String guest_info) {
			this.guest_info = guest_info;
		}
		public int getGuest_fee() {
			return guest_fee;
		}
		public void setGuest_fee(int guest_fee) {
			this.guest_fee = guest_fee;
		}
		public String getGuest_state() {
			return guest_state;
		}
		public void setGuest_state(String guest_state) {
			this.guest_state = guest_state;
		}
		
		
//		구장 신청 리스트
		
		private Date booking_date;
		private String booking_start_time;
		private String booking_end_time;
		
//		구장 시작,종료 시간 합 결과
		private String guest_time;
		

		public Date getBooking_date() {
			return booking_date;
		}
		public void setBooking_date(Date booking_date) {
			this.booking_date = booking_date;
		}
		public String getBooking_start_time() {
			return booking_start_time;
		}
		public void setBooking_start_time(String booking_start_time) {
			this.booking_start_time = booking_start_time;
		}
		public String getBooking_end_time() {
			return booking_end_time;
		}
		public void setBooking_end_time(String booking_end_time) {
			this.booking_end_time = booking_end_time;
		}
		public String getGuest_time() {
			return guest_time;
		}
		public void setGuest_time(String guest_time) {
			this.guest_time = guest_time;
		}
		
		
//		구장 이름
		private String court_name;
		
//		구장예약 시간
		private String court_time;

		public String getCourt_name() {
			return court_name;
		}
		public void setCourt_name(String court_name) {
			this.court_name = court_name;
		}
		public String getCourt_time() {
			return court_time;
		}
		public void setCourt_time(String court_time) {
			this.court_time = court_time;
		}
}
