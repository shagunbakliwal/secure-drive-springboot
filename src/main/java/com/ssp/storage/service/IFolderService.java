package com.ssp.storage.service;

import com.ssp.storage.beans.FolderData;
import com.ssp.storage.domain.Folder;

public interface IFolderService {

	public FolderData getRootFolder(String userName);

	public Folder addFolder(String userName, String folderName, String parent, int level);
	
	public FolderData getFolder(String userName, String folderName, String parent, int level);
}
