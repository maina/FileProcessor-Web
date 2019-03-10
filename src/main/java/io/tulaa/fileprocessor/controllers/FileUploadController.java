package io.tulaa.fileprocessor.controllers;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.tulaa.fileprocessor.services.CsvReader;
import io.tulaa.fileprocessor.utils.ApiResponse;
import io.tulaa.fileprocessor.utils.Utils;

@RestController
@RequestMapping("/files")
public class FileUploadController {

	@Autowired
	CsvReader csvReader;

	@PostMapping("")
	public ResponseEntity<ApiResponse> handleFileUpload(@RequestParam("file") MultipartFile multipartFile,
			RedirectAttributes redirectAttributes) throws IllegalStateException, IOException {
		
		File convFile = Utils.convert(multipartFile);
		boolean result=csvReader.processFile(convFile);
		
		ApiResponse response= new ApiResponse();
		response.setSuccess(result);
		response.setMessage(result?"Successfully processed file: "+ multipartFile.getOriginalFilename():"Unable to process file");

		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}
	
	

}
