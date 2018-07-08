/*package com.ssp.storage.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssp.storage.service.IFolderService;
import com.ssp.storage.web.ResponseEntity;

@Controller
@RequestMapping("/")
public class FolderController {
	Logger logger = LoggerFactory.getLogger(FolderController.class);

	@Autowired
	IFolderService folderFilesService;

	@GetMapping("/api/folder/root")
	public ResponseEntity<?> getRootFolder(@RequestHeader String userName) {
		System.out.println(folderFilesService.getRootFolder(userName));
		return new ResponseEntity<>(folderFilesService.getRootFolder(userName), HttpStatus.OK);

	}

	@GetMapping("/addFolder")
	public ResponseEntity<?> addFolder(@RequestHeader String userName, @RequestHeader String folderName,
			@RequestHeader String parent, @RequestHeader int level) {
		return new ResponseEntity<>(folderFilesService.addFolder(userName, folderName, parent, level), HttpStatus.OK);
	}

	@GetMapping("/getFolder")
	public ResponseEntity<?> getFolder(@RequestHeader String userName, @RequestHeader String folderName,
			@RequestHeader String parent, @RequestHeader int level) {
		return new ResponseEntity<>(folderFilesService.getFolder(userName, folderName, parent, level), HttpStatus.OK);
	}

}
*/