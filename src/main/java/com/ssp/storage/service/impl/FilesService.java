package com.ssp.storage.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ssp.storage.beans.FileBean;
import com.ssp.storage.constant.ErrorCode;
import com.ssp.storage.domain.File;
import com.ssp.storage.domain.Folder;
import com.ssp.storage.exception.FileException;
import com.ssp.storage.repository.FileRepository;
import com.ssp.storage.repository.FolderRepository;
import com.ssp.storage.repository.UserSecurityQuestionRepository;
import com.ssp.storage.service.IFilesService;

@Service
public class FilesService implements IFilesService {

	@Autowired
	FolderRepository folderRepository;

	@Autowired
	FileRepository fileRepository;

	@Autowired
	UserSecurityQuestionRepository userSecurityQuestionRepository;

	@Value("${server.port}")
	private int port;
	
	@Override
	public boolean addFile(MultipartFile file, String parent, String userName, String absolutePath, int level) {
		Folder parentFolder = folderRepository.findByUserUsernameAndFolderNameAndLevel(userName, parent, level);
		byte[] bytes = null;
		try {
			bytes = file.getBytes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		File Dfile = new File();
		Dfile.setFileName(file.getOriginalFilename());
		Dfile.setFile(bytes);
		Dfile.setExtension(file.getOriginalFilename().lastIndexOf(".") != -1
				? file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."))
				: null);
		Dfile.setFolder(parentFolder);
		Dfile.setAbsolutePath(absolutePath);
		List<File> childFiles = parentFolder.getFiles();
		childFiles.add(Dfile);
		if (Objects.nonNull(folderRepository.save(parentFolder)))
			return true;
		return false;
	}

	@Override
	public FileBean getFile(String folder, String parentFolder, String userName, String fileName, String tracePath,
			int level) throws FileException {
		Folder parentFolderFile = folderRepository.findByUserUsernameAndFolderNameAndParentFolderNameAndLevel(userName,
				folder, parentFolder, level);
		File file = fileRepository.findByFileNameAndFolderId(fileName, parentFolderFile.getId());
		if (file == null) {
			throw new FileException(port + ErrorCode.INVALID_FILE.getKey(), ErrorCode.INVALID_FILE.getValue());
		}
		FileBean fileBean = new FileBean();
		if (Objects.nonNull(file.getAbsolutePath())) {
			if (file.getAbsolutePath().equals(tracePath)) {
				fileBean.setFile(file);
			} else {
				fileBean.setListOfQuestions(userSecurityQuestionRepository.findAllByUserUsername(userName).stream()
						.map(a -> a.getQuestion()).collect(Collectors.toList()));
			}
		}
		return fileBean;
	}

}
