package com.emailschedularquartz.readexcel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ExcelToDatabase {

	public static void main(String[] args) throws SQLException, IOException {
		// Database Connection
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quartz_Demo","root","Aakash4ever");
		Statement stmt = con.createStatement();
		
		// Create a new table in Database 'emails'
		String sql = "create table emails ( First_Name varchar(100) , Last_Name varchar(100), email varchar(200)";
		stmt.execute(sql);
		
		
		// Excel
		FileInputStream fis = new FileInputStream(".\\");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet=workbook.getSheet("Sheet1");
		
		int rows = sheet.getLastRowNum();
		
		for(int r=1 ; r<rows ; r++){
			XSSFRow row = sheet.getRow(r);
			String fName = row.getCell(0).getStringCellValue();
			String lName = row.getCell(1).getStringCellValue();
			String mail  = row.getCell(2).getStringCellValue();
			
			
			sql = "insert into emails values('"+fName+"', '"+lName+"','"+mail+"' )";
			stmt.execute(sql);
			stmt.execute("commit");
		}
		
		workbook.close();
		fis.close();
		con.close();
		
	} 

}
