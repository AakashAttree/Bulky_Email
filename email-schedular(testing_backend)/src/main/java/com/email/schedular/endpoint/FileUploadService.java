package com.email.schedular.endpoint;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.email.schedular.db.pojo.Contact;
import com.email.schedular.db.pojo.ContactEmailRelationship;
import com.email.schedular.db.repo.ContactEmailRelationshipRepo;
import com.email.schedular.db.repo.ContactRepo;

@Service
public class FileUploadService {

	@Autowired
	ContactRepo contactRepo;
	@Autowired
	ContactEmailRelationshipRepo contactEmailRelationshipRepo;


	public void uploadFile(MultipartFile file, Long emailId) throws IllegalStateException, IOException {

		InputStream inputStream = file.getInputStream();
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		XSSFSheet sheet=workbook.getSheet("Sheet1");

		int rows = sheet.getLastRowNum();
		List<String> emails = new ArrayList<>();
		for(int r=0 ; r<rows ; r++){

			XSSFRow row = sheet.getRow(r);
			String fName = row.getCell(0).getStringCellValue();
			String lName = row.getCell(1).getStringCellValue();
			String mail  = row.getCell(2).getStringCellValue();
			if(!emails.contains(mail)) {
				Contact contact = contactRepo.findByEmail(mail);
				if(contact == null)
				{
					contact = new Contact();
					contact.setFirstName(fName);
					contact.setLastName(lName);
					contact.setEmail(mail);
					System.out.println(fName );
					contact = contactRepo.save(contact);
				}
				ContactEmailRelationship contactEmailRelationship = new ContactEmailRelationship();
				contactEmailRelationship.setEmailid(emailId);
				contactEmailRelationship.setContactid(contact.getId());
				contactEmailRelationshipRepo.save(contactEmailRelationship);
				emails.add(mail);
			}
		}
		workbook.close();
		//file.transferTo(new File("" + file.getOriginalFilename()));
	}
}
