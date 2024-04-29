package com.back.admin.dto;

public class AdminWriteDTO {
	
	private int official_match_idx;
	private int court_idx;
	private String official_match_gender;
	private String official_match_level;
	private int official_match_to;
	private String official_match_to_state;
	private String official_match_info;
	private int official_match_fee;
	private String official_match_date;
	private String official_match_start_time;
	private String official_match_end_time;
	private String official_match_state;
	private String	official_match_post_state;
	
	private String court_address;
	private int currentCount;
	
	public int getOfficial_match_idx() {
		return official_match_idx;
	}
	public void setOfficial_match_idx(int official_match_idx) {
		this.official_match_idx = official_match_idx;
	}
	public int getCourt_idx() {
		return court_idx;
	}
	public void setCourt_idx(int court_idx) {
		this.court_idx = court_idx;
	}
	public String getOfficial_match_gender() {
		return official_match_gender;
	}
	public void setOfficial_match_gender(String official_match_gender) {
		this.official_match_gender = official_match_gender;
	}
	public String getOfficial_match_level() {
		return official_match_level;
	}
	public void setOfficial_match_level(String official_match_level) {
		this.official_match_level = official_match_level;
	}
	public int getOfficial_match_to() {
		return official_match_to;
	}
	public void setOfficial_match_to(int official_match_to) {
		this.official_match_to = official_match_to;
	}
	public String getOfficial_match_to_state() {
		return official_match_to_state;
	}
	public void setOfficial_match_to_state(String official_match_to_state) {
		this.official_match_to_state = official_match_to_state;
	}
	public String getOfficial_match_info() {
		return official_match_info;
	}
	public void setOfficial_match_info(String official_match_info) {
		this.official_match_info = official_match_info;
	}
	public int getOfficial_match_fee() {
		return official_match_fee;
	}
	public void setOfficial_match_fee(int official_match_fee) {
		this.official_match_fee = official_match_fee;
	}
	public String getOfficial_match_date() {
		return official_match_date;
	}
	public void setOfficial_match_date(String official_match_date) {
		this.official_match_date = official_match_date;
	}
	public String getOfficial_match_start_time() {
		return official_match_start_time;
	}
	public void setOfficial_match_start_time(String official_match_start_time) {
		this.official_match_start_time = official_match_start_time;
	}
	public String getOfficial_match_end_time() {
		return official_match_end_time;
	}
	public void setOfficial_match_end_time(String official_match_end_time) {
		this.official_match_end_time = official_match_end_time;
	}
	public String getOfficial_match_state() {
		return official_match_state;
	}
	public void setOfficial_match_state(String official_match_state) {
		this.official_match_state = official_match_state;
	}
	public String getOfficial_match_post_state() {
		return official_match_post_state;
	}
	public void setOfficial_match_post_state(String official_match_post_state) {
		this.official_match_post_state = official_match_post_state;
	}
	public String getCourt_address() {
		return court_address;
	}
	public void setCourt_address(String court_address) {
		this.court_address = court_address;
	}
	public int getCurrentCount() {
		return currentCount;
	}
	public void setCurrentCount(int currentCount) {
		this.currentCount = currentCount;
	}
	

	private int officialIdx;
	private int courtIdx;
	private String officialGender;
	private String officialLevel;
	private int officialTO;
	private String officialTOState;
	private String officialInfo;
	private int officialPrice;
	private String officialDate;
	private int officialStartTime;
	private int officialEndTime;
	private String officialMatchState;
	private String officialState;

	public int getCourtIdx() {
		return courtIdx;
	}

	public void setCourtIdx(int courtIdx) {
		this.courtIdx = courtIdx;
	}

	public String getOfficialGender() {
		return officialGender;
	}

	public void setOfficialGender(String officialGender) {
		this.officialGender = officialGender;
	}

	public String getOfficialLevel() {
		return officialLevel;
	}

	public void setOfficialLevel(String officialLevel) {
		this.officialLevel = officialLevel;
	}

	public String getOfficialTOState() {
		return officialTOState;
	}

	public void setOfficialTOState(String officialTOState) {
		this.officialTOState = officialTOState;
	}

	public String getOfficialInfo() {
		return officialInfo;
	}

	public void setOfficialInfo(String officialInfo) {
		this.officialInfo = officialInfo;
	}

	public int getOfficialPrice() {
		return officialPrice;
	}

	public void setOfficialPrice(int officialPrice) {
		this.officialPrice = officialPrice;
	}

	public String getOfficialDate() {
		return officialDate;
	}

	public void setOfficialDate(String officialDate) {
		this.officialDate = officialDate;
	}

	public int getOfficialStartTime() {
		return officialStartTime;
	}

	public void setOfficialStartTime(int officialStartTime) {
		this.officialStartTime = officialStartTime;
	}

	public int getOfficialEndTime() {
		return officialEndTime;
	}

	public void setOfficialEndTime(int officialEndTime) {
		this.officialEndTime = officialEndTime;
	}

	public String getOfficialMatchState() {
		return officialMatchState;
	}

	public void setOfficialMatchState(String officialMatchState) {
		this.officialMatchState = officialMatchState;
	}

	public String getOfficialState() {
		return officialState;
	}

	public void setOfficialState(String officialState) {
		this.officialState = officialState;
	}

	public int getOfficialTO() {
		return officialTO;
	}

	public void setOfficialTO(int officialTO) {
		this.officialTO = officialTO;
	}

	public int getOfficialIdx() {
		return officialIdx;
	}

	public void setOfficialIdx(int officialIdx) {
		this.officialIdx = officialIdx;
	}
	
	// team
	private int team_idx;
	private String id;
	private String logo;
	private String team_name;
	private String team_address;
	private String team_level;
	private String team_info;

	public int getTeam_idx() {
		return team_idx;
	}
	public void setTeam_idx(int team_idx) {
		this.team_idx = team_idx;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getTeam_name() {
		return team_name;
	}
	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}
	public String getTeam_address() {
		return team_address;
	}
	public void setTeam_address(String team_address) {
		this.team_address = team_address;
	}
	public String getTeam_level() {
		return team_level;
	}
	public void setTeam_level(String team_level) {
		this.team_level = team_level;
	}
	public String getTeam_info() {
		return team_info;
	}
	public void setTeam_info(String team_info) {
		this.team_info = team_info;
	}
	
	// team_join
	private int join_team_idx;
	private int join_team_to;
	private String join_team_content;
	private String join_team_level;
	private String join_team_gender;
	private String join_team_position;
	private String join_team_state;

	public int getJoin_team_idx() {
		return join_team_idx;
	}
	public void setJoin_team_idx(int join_team_idx) {
		this.join_team_idx = join_team_idx;
	}
	public int getJoin_team_to() {
		return join_team_to;
	}
	public void setJoin_team_to(int join_team_to) {
		this.join_team_to = join_team_to;
	}
	public String getJoin_team_content() {
		return join_team_content;
	}
	public void setJoin_team_content(String join_team_content) {
		this.join_team_content = join_team_content;
	}
	public String getJoin_team_level() {
		return join_team_level;
	}
	public void setJoin_team_level(String join_team_level) {
		this.join_team_level = join_team_level;
	}
	public String getJoin_team_gender() {
		return join_team_gender;
	}
	public void setJoin_team_gender(String join_team_gender) {
		this.join_team_gender = join_team_gender;
	}
	public String getJoin_team_position() {
		return join_team_position;
	}
	public void setJoin_team_position(String join_team_position) {
		this.join_team_position = join_team_position;
	}
	public String getJoin_team_state() {
		return join_team_state;
	}
	public void setJoin_team_state(String join_team_state) {
		this.join_team_state = join_team_state;
	}
	
	
}



















