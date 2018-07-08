package com.ssp.storage.beans;

import java.util.List;

public class FolderData {

	private List<String> listOFFolder;
	
	private List<String> listOfFiles;

	public List<String> getListOFFolder() {
		return listOFFolder;
	}

	public void setListOFFolder(List<String> listOFFolder) {
		this.listOFFolder = listOFFolder;
	}

	public List<String> getListOfFiles() {
		return listOfFiles;
	}

	public void setListOfFiles(List<String> listOfFiles) {
		this.listOfFiles = listOfFiles;
	}

	@Override
	public String toString() {
		return "FolderData [listOFFolder=" + listOFFolder + ", listOfFiles=" + listOfFiles + "]";
	}
	
	
	
	
}
