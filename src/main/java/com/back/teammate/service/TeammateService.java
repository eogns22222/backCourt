package com.back.teammate.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.teammate.dao.TeammateDAO;
import com.back.teammate.dto.TeammateDTO;

@Service
public class TeammateService {
   Logger logger = LoggerFactory.getLogger(getClass());
   @Autowired TeammateDAO teammateDAO;
   
   public Map<String, Object> pageList(int page, String address, String position, String level) {

      Map<String, Object> result = new HashMap<String, Object>();
      List<TeammateDTO> list = null;
      List<TeammateDTO> allList = teammateDAO.allList();

      int start = (page - 1) * 10;
      
      if (address.equals("") == true && position.equals("") == true && level.equals("") == true) {
          list = teammateDAO.list(start);
          result.put("totalPage", teammateDAO.allTeammateCount());
      } else if (address.equals("") == true && position.equals("") == true && level.equals("") == false) {
          list = teammateDAO.listFilterLevel(start ,level);
          result.put("totalPage", teammateDAO.levelFilteringTeammateCount(level));
      } else if (address.equals("") == true && position.equals("") == false && level.equals("") == true) {
          list = teammateDAO.listFilterPosition(start ,position);
          result.put("totalPage", teammateDAO.positionFilteringTeammateCount(position));
      } else if (address.equals("") == true && position.equals("") == false && level.equals("") == false) {
          list = teammateDAO.listFilterPosotionLevel(start ,position,level);
          result.put("totalPage", teammateDAO.positionLevelFilteringTeammateCount(position,level));
      } else if (address.equals("") == false && position.equals("") == true && level.equals("") == true) {
          list = teammateDAO.listFilterAddress(start ,address);
          result.put("totalPage", teammateDAO.addressFilteringTeammateCount(address));      
      } else if (address.equals("") == false && position.equals("") == true && level.equals("") == false) {
          list = teammateDAO.listFilterAddressLevel(start ,address, level);
          result.put("totalPage", teammateDAO.addressLevelFilteringTeammateCount(address, level));
      } else if (address.equals("") == false && position.equals("") == false && level.equals("") == true) {
          list = teammateDAO.listFilterAddressPosition(start ,address, position);
          result.put("totalPage", teammateDAO.addressPositionFilteringTeammateCount(address, position));
      } else if (address.equals("") == false && position.equals("") == false && level.equals("") == false) {
          list = teammateDAO.listFilterAddressPositionLevel(start ,address, position, level);
          result.put("totalPage", teammateDAO.allFilteringTeammateCount(address, position,level));
      }
			

      result.put("list", list);
      result.put("allList", allList);
      return result;
   }

public Map<String, Object> searchList(String teammateSearchCategory, String teammateSearchWord, int page, String address, String id, String teamName) {
	Map<String, Object> result = new HashMap<String, Object>();
	List<TeammateDTO> list = null;
	List<TeammateDTO> allList = teammateDAO.allList();
	
	int start = (page - 1) * 10;
	
	if(teammateSearchCategory.equals("teamAddress")) {
		list = teammateDAO.addressSearchList(teammateSearchWord,start);
		logger.info("서치리스트 코트어드레스 ");
		result.put("list", list);
		result.put("totalPage", teammateDAO.addressFilteringCount(teammateSearchWord));
	}else if(teammateSearchCategory.equals("teamName")) {
		list = teammateDAO.teamnameSearchList(teammateSearchWord,start);
		logger.info("서치리스트 팀명 ");
		result.put("list", list);
		result.put("totalPage", teammateDAO.teamnameFilteringCount(teammateSearchWord));
	}else if(teammateSearchCategory.equals("representName")) {
		list = teammateDAO.representSearchList(teammateSearchWord,start);
		logger.info("서치리스트 팀장ID ");
		result.put("list", list);
		result.put("totalPage", teammateDAO.representFilteringCount(teammateSearchWord));
	}


	result.put("list", list);
	result.put("allList", allList);
	return result;
}
   
   

}