package com.back.mypage.dto;

import java.sql.Date;

public class MypageDTO {
	
	private int report_write_idx;
	private String report_write_type;
	private String report_content;
	private String id;
	private String report_tit;
	
	
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
		
	
}
