package com.email.schedular.endpoint;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class FileUploadController {
	@Autowired
	FileUploadService fileUploadService;

	@PostMapping(value = "/upload/{emailId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public void uploadFile(@RequestPart("file") MultipartFile file
			,@PathVariable("emailId") Long emailId) throws IllegalStateException, IOException {
		 fileUploadService.uploadFile(file, emailId);
	}
	
}
