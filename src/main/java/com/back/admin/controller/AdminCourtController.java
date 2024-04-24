package com.back.admin.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.back.admin.service.AdminCourtService;

@Controller
public class AdminCourtController {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	AdminCourtService adminCourtService;

	@RequestMapping(value = "/admin/courtList.go")
	public String listGo() {
		return "/admin/court_list";
	}

	@RequestMapping(value = "/admin/courtList.ajax", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> callList(String currentPage, String address, String searchCategory, String searchWord,
			String searchFlag) {
		logger.info("listCall / currentPage = {} / address = {} / ", currentPage, address);
		logger.info(searchFlag);
		int page = Integer.parseInt(currentPage);
		if (searchFlag.equals("true")) {
			return adminCourtService.searchList(searchCategory, searchWord, page, address);
		} else {
			return adminCourtService.list(page, address);
		}

	}

	@RequestMapping(value = "/admin/courtWrite.go")
	public String WriteGo() {
		return "/admin/court_register";
	}
	
	@RequestMapping(value = "/admin/courtWrite.ajax")
	@ResponseBody
	public Map<String, Boolean> Write(
            @RequestParam("file") MultipartFile[] files,
            @RequestParam("courtWriteName") String courtWriteName,
            @RequestParam("courtWriteInfo") String courtWriteInfo,
            @RequestParam("courtWritePrice") String courtWritePrice,
            @RequestParam("courtWriteAddress") String courtWriteAddress,
            @RequestParam("courtIsOfficial") String courtIsOfficial,
            @RequestParam("courtIsDisabled") String courtIsDisabled){
		
		logger.info("courtWriteName: {}", courtWriteName);
        logger.info("courtWriteInfo: {}", courtWriteInfo);
        logger.info("courtWritePrice: {}", courtWritePrice);
        logger.info("courtWriteAddress: {}", courtWriteAddress);
        logger.info("courtIsOfficial: {}", courtIsOfficial);
        logger.info("courtIsDisabled: {}", courtIsDisabled);
        
        
        // 파일 정보도 로깅
        for (MultipartFile file : files) {
            logger.info("File Name: {}", file.getOriginalFilename());
            logger.info("Content Type: {}", file.getContentType());
            logger.info("File Size: {}", file.getSize());
        }
		
		return adminCourtService.write(files,courtWriteName,courtWriteInfo,courtWritePrice,courtWriteAddress,courtIsOfficial,courtIsDisabled);
	}
}
