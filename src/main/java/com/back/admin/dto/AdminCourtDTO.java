package com.back.admin.dto;

public class AdminCourtDTO {

	private int courtIdx;
	private String courtName;
	private String courtAddress;
	private String courtState;
	
	public int getCourtIdx() {
		return courtIdx;
	}
	public void setCourtIdx(int courtIdx) {
		this.courtIdx = courtIdx;
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
	public String getCourtState() {
		return courtState;
	}
	public void setCourtState(String courtState) {
		this.courtState = courtState;
	}
}
