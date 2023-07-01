package com.emailschedularquartz.uploadfile.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	

	public void uploadFile(MultipartFile file) throws IllegalStateException, IOException {
		
		
		
		InputStream inputStream = file.getInputStream();
		//FileInputStream fis = new FileInputStream(inputStream);
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		XSSFSheet sheet=workbook.getSheet("Sheet1");
		
		int rows = sheet.getLastRowNum();
		
		for(int r=1 ; r<rows ; r++){
			XSSFRow row = sheet.getRow(r);
			String fName = row.getCell(0).getStringCellValue();
			String lName = row.getCell(1).getStringCellValue();
			String mail  = row.getCell(2).getStringCellValue();
			
			System.out.println(fName );
			jdbcTemplate.execute("insert into emails values('"+fName+"', '"+lName+"','"+mail+"' )");
		}
		//file.transferTo(new File("" + file.getOriginalFilename()));
	}
}
