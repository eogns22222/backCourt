package com.back.mypage.dto;

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
	
	
}
