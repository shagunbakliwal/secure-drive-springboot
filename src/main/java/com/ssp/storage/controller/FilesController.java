/*package com.ssp.storage.controller;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssp.storage.beans.FileBean;
import com.ssp.storage.constant.ErrorCode;
import com.ssp.storage.error.AcgError;
import com.ssp.storage.service.IFilesService;
import com.ssp.storage.web.ResponseEntity;

@RestController
@RequestMapping("/api/files")
public class FilesController {

	Logger logger = LoggerFactory.getLogger(FilesController.class);

	@Autowired
	IFilesService fileService;

	@PostMapping("/addFile")
	public ResponseEntity<?> addFile(@RequestBody MultipartFile file, @RequestHeader String parent,
			@RequestHeader String userName, @RequestHeader String absolutePath, @RequestHeader int level) {
		return new ResponseEntity<>(fileService.addFile(file, parent, userName, absolutePath, level), HttpStatus.OK);
	}

	@GetMapping("/getFile")
	public ResponseEntity<?> getFile(@RequestHeader String folder, @RequestHeader String parentFolder,
			@RequestHeader String userName, @RequestHeader String fileName, HttpServletResponse response,
			@RequestHeader String tracePath, @RequestHeader int level) {
		try {
			FileBean file = fileService.getFile(folder, parentFolder, userName, fileName, tracePath, level);
			if (file.getFile() != null) {
				response.setContentType("application/OCTET-STREAM");
				response.setHeader("Content-Disposition", "attachment; filename=" + file.getFile().getFileName());
				ServletOutputStream sos = response.getOutputStream();
				sos.write(file.getFile().getFile());
				sos.flush();
				return new ResponseEntity<>("Success", HttpStatus.OK);
			} else {
				return new ResponseEntity<>(file.getListOfQuestions(), HttpStatus.OK);
			}
		} catch (Exception e) {
			//return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<>(
					new AcgError(ErrorCode.DOWNLOAD_FAILED.getKey(), ErrorCode.DOWNLOAD_FAILED.getValue()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(
				new AcgError(ErrorCode.DOWNLOAD_FAILED.getKey(), ErrorCode.DOWNLOAD_FAILED.getValue()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
*/