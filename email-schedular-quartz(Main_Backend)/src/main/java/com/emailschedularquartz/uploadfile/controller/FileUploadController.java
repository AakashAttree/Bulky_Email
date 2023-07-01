package com.emailschedularquartz.uploadfile.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.emailschedularquartz.uploadfile.service.FileUploadService;

@RestController
public class FileUploadController {
	@Autowired
	FileUploadService fileUploadService;

	@PostMapping("/upload")
	public void uploadFile(@RequestParam("File") MultipartFile file) throws IllegalStateException, IOException {
		 fileUploadService.uploadFile(file);
	}
	
}
