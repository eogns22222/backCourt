package com.back.teammate.dto;

public class TeammateDTO {

   private int join_team_idx;
   private int team_idx;
   private int join_team_to;
   private String join_to_content;
   private String join_to_level;
   private String join_to_gender;
   private String join_to_position;

   
   private String team_name;
   private String id;
   private String team_address;
   private String logo;
   
   
   public int getJoin_team_idx() {
      return join_team_idx;
   }
   public void setJoin_team_idx(int join_team_idx) {
      this.join_team_idx = join_team_idx;
   }
   public int getTeam_idx() {
      return team_idx;
   }
   public void setTeam_idx(int team_idx) {
      this.team_idx = team_idx;
   }
   public int getJoin_team_to() {
      return join_team_to;
   }
   public void setJoin_team_to(int join_team_to) {
      this.join_team_to = join_team_to;
   }
   public String getJoin_to_content() {
      return join_to_content;
   }
   public void setJoin_to_content(String join_to_content) {
      this.join_to_content = join_to_content;
   }
   public String getJoin_to_level() {
      return join_to_level;
   }
   public void setJoin_to_level(String join_to_level) {
      this.join_to_level = join_to_level;
   }
   public String getJoin_to_gender() {
      return join_to_gender;
   }
   public void setJoin_to_gender(String join_to_gender) {
      this.join_to_gender = join_to_gender;
   }
   public String getJoin_to_position() {
      return join_to_position;
   }
   public void setJoin_to_position(String join_to_position) {
      this.join_to_position = join_to_position;
   }
   public String getTeam_name() {
      return team_name;
   }
   public void setTeam_name(String team_name) {
      this.team_name = team_name;
   }
   public String getId() {
      return id;
   }
   public void setId(String id) {
      this.id = id;
   }
   public String getTeam_address() {
      return team_address;
   }
   public void setTeam_address(String team_address) {
      this.team_address = team_address;
   }
public String getLogo() {
	return logo;
}
public void setLogo(String logo) {
	this.logo = logo;
}
}