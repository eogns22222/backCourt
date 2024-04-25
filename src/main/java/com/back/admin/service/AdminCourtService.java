package com.back.admin.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.back.admin.dao.AdminCourtDAO;
import com.back.admin.dto.AdminCourtDTO;

@Service
public class AdminCourtService {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	AdminCourtDAO adminCourtDAO;

	@Autowired
	private ServletContext servletContext;

	public Map<String, Object> list(int page, String address) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<AdminCourtDTO> list;
		List<AdminCourtDTO> allList = adminCourtDAO.allList();

		int start = (page - 1) * 10;

		if (address.equals("") == true) {
//			logger.info("servcie.list / address = {} /", address);
			list = adminCourtDAO.list(start);
			result.put("totalPage", adminCourtDAO.allCourtCount());

		} else {
//			logger.info("servcie.list / address = {} /", address);
			list = adminCourtDAO.listFilterAddress(start, address);
			result.put("totalPage", adminCourtDAO.addressFilteringCourtCount(address));

		}

		result.put("list", list);
		result.put("allList", allList);
		return result;
	}

	public Map<String, Object> searchList(String courtSearchCategory, String courtSearchWord, int page,
			String address) {

		Map<String, Object> result = new HashMap<String, Object>();
		List<AdminCourtDTO> list;
		List<AdminCourtDTO> allList = adminCourtDAO.allList();

		int start = (page - 1) * 10;

		if (courtSearchCategory.equals("courtAddress")) {
			list = adminCourtDAO.addressSearchList(courtSearchWord, start);
			logger.info("서치리스트 코트어드레스 ");
			result.put("list", list);
			result.put("totalPage", adminCourtDAO.addressFilteringCourtCount(courtSearchWord));
		} else if (courtSearchCategory.equals("courtName")) {
			list = adminCourtDAO.nameSearchList(courtSearchWord, start);
			logger.info("서치리스트 코트네임 ");
			result.put("list", list);
			result.put("totalPage", adminCourtDAO.nameFilteringCourtCount(courtSearchWord));
		}

//		if (address.equals("") == true) {
////			logger.info("servcie.list / address = {} /", address);
//			list = courtDAO.SearchList(courtSearchCategory,courtSearchWord,start);
//			result.put("totalPage", courtDAO.allCourtCount());
//
//		} else {
////			logger.info("servcie.list / address = {} /", address);
//			list = courtDAO.listFilterAddress(start, address);
//			result.put("totalPage", courtDAO.addressFilteringCourtCount(address));
//
//		}

		result.put("allList", allList);
		return result;
	}

	public Map<String, Boolean> write(MultipartFile[] files, String courtWriteName, String courtWriteInfo,
			String courtWritePrice, String courtWriteAddress, String courtIsOfficial, String courtIsDisabled) {
		Map<String, Boolean> map = new HashMap<String, Boolean>();

		AdminCourtDTO dto = new AdminCourtDTO();

		dto.setCourtName(courtWriteName);
		dto.setCourtInfo(courtWriteInfo);
		dto.setCourtPrice(courtWritePrice);
		dto.setCourtAddress(courtWriteAddress);
		dto.setCourtOfficial(courtIsOfficial);
		dto.setCourtState(courtIsDisabled);

		map.put("result", adminCourtDAO.write(dto));

		int idx = dto.getCourtIdx();

		logger.info("idx = " + idx);
		logger.info(map.get("result") + "");

		courtImageUploading(idx, files);

		return null;
	}

	public void courtImageUploading(int idx, MultipartFile[] files) {

		String os = System.getProperty("os.name").toLowerCase();
		
		logger.info(os);
		String directory = "";
		if (os.contains("mac")) {
			directory = "/Users/chaehyeonpark/Documents/gdj78_backcourt/upload/court/";
		} else if (os.contains("win")) {
			directory = "C:/upload/court/";
		}
		File dirPath = new File(directory);
		logger.info("경로 " + directory);
		Path path = Paths.get(directory);
        try {
			Files.createDirectories(path.getParent());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// 폴더가 존재하지 않으면 생성
		if (!dirPath.exists()) {
			dirPath.mkdirs();
			logger.info("폴더가 생성되었습니다.");
		}

		int count = 1;
		for (MultipartFile file : files) {
			String fileName = file.getOriginalFilename();
			String newFileName = "court" + idx + "_image" + count + ".png";
			Path filePath = Paths.get(directory+ newFileName);

			try {
				byte[] bytes = file.getBytes();
				Files.write(filePath, bytes);
				adminCourtDAO.courtImageUpload(Integer.toString(idx), newFileName);
			} catch (IOException e) {
				logger.error("Failed to write file: " + filePath, e);
			}
			count++;
		}
	}

//	public void courtImageUploading(int idx, MultipartFile[] files) {
//		String absolutePath = servletContext.getRealPath(relativePath);
//		Path path = Paths.get(absolutePath);
//		logger.info(fileRoot);
//		int count = 1;
//		for (MultipartFile file : files) {
//			String fileName = file.getOriginalFilename();
//			logger.info(fileName);
//
//			String newFileName = "court" + idx + "_image" + count + ".png";
//
//			try {
//				byte[] bytes = file.getBytes();
//				Path path = Paths.get(fileRoot + newFileName);
//				Files.write(path, bytes);
//				adminCourtDAO.courtImageUpload(Integer.toString(idx), newFileName);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}

//        String absolutePath = servletContext.getRealPath(relativePath);
//        Path path = Paths.get(absolutePath);
//        logger.info(fileRoot);
//		int count = 1;
//		for (MultipartFile file : files) {
//			String fileName = file.getOriginalFilename();
//			logger.info(fileName);
//
//			String newFileName = "court" + idx + "_image" + count + ".png";
//
//			try {
//				byte[] bytes = file.getBytes();
//				Path path = Paths.get(fileRoot + newFileName);
//				Files.write(path, bytes);
//				adminCourtDAO.courtImageUpload(Integer.toString(idx), newFileName);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
}
