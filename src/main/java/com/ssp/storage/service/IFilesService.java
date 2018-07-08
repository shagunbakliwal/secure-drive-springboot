package com.ssp.storage.service;

import org.springframework.web.multipart.MultipartFile;

import com.ssp.storage.beans.FileBean;
import com.ssp.storage.exception.FileException;

public interface IFilesService {

	public boolean addFile(MultipartFile file, String parent, String userName, String absolutePath, int level);
	
	public FileBean getFile(String folder, String parentFolder, String userName, String fileName, String tracePath, int level) throws FileException;
}
