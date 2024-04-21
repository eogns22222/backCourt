package com.back.admin.dto;

import java.sql.Date;

public class AdminReportDTO {
	private int reportIdx;
	private String reportState;
	private String reportSubject;
	private String reportId;
	private Date reportDate;
	private String reportAdminId;
	
	public int getReportIdx() {
		return reportIdx;
	}
	public void setReportIdx(int reportIdx) {
		this.reportIdx = reportIdx;
	}
	public String getReportState() {
		return reportState;
	}
	public void setReportState(String reportState) {
		this.reportState = reportState;
	}
	public String getReportSubject() {
		return reportSubject;
	}
	public void setReportSubject(String reportSubject) {
		this.reportSubject = reportSubject;
	}
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public Date getReportDate() {
		return reportDate;
	}
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}
	public String getReportAdminId() {
		return reportAdminId;
	}
	public void setReportAdminId(String reportAdminId) {
		this.reportAdminId = reportAdminId;
	}
}
