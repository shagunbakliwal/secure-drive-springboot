package com.ssp.storage.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssp.storage.beans.FolderData;
import com.ssp.storage.domain.Folder;
import com.ssp.storage.repository.FolderRepository;
import com.ssp.storage.service.IFolderService;

@Service
public class FolderService implements IFolderService {

	@Autowired
	FolderRepository folderRepository;

	@Override
	public FolderData getRootFolder(String userName) {

		Folder folder = folderRepository.findByUserUsernameAndRootTrue(userName);

		FolderData folderData = new FolderData();
		List<String> listOfFiles = new ArrayList<>();
		List<String> listOfFolder = new ArrayList<>();
		if (folder != null) {
			folder.getFiles().forEach(x -> listOfFiles.add(x.getFileName()));
			folderData.setListOfFiles(listOfFiles);
			folder.getChildren().forEach(x -> listOfFolder.add(x.getFolderName()));
		}
		folderData.setListOFFolder(listOfFolder);
		return folderData;
	}

	@Override
	public Folder addFolder(String userName, String folderName, String parent, int level) {
		Folder parentFolder = folderRepository.findByUserUsernameAndFolderNameAndLevel(userName, parent, level - 1);
		Folder folder = new Folder();
		folder.setFolderName(folderName);
		folder.setParent(parentFolder);
		folder.setRoot(false);
		folder.setUser(parentFolder.getUser());
		folder.setLevel(level);
		List<Folder> children = parentFolder.getChildren();
		children.add(folder);
		parentFolder.setChildren(children);
		folderRepository.save(parentFolder);
		return folder;
	}

	@Override
	public FolderData getFolder(String userName, String folderName, String parent, int level) {

		Folder folder = folderRepository.findByUserUsernameAndFolderNameAndParentFolderNameAndLevel(userName,
				folderName, parent, level);
		FolderData folderData = new FolderData();
		List<String> listOfFiles = new ArrayList<>();
		List<String> listOfFolder = new ArrayList<>();
		folder.getFiles().forEach(x -> listOfFiles.add(x.getFileName()));
		folderData.setListOfFiles(listOfFiles);
		folder.getChildren().forEach(x -> listOfFolder.add(x.getFolderName()));
		folderData.setListOFFolder(listOfFolder);
		return folderData;
	}

}
