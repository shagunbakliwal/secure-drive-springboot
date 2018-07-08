package com.ssp.storage.beans;

import java.util.List;

import com.ssp.storage.domain.File;

public class FileBean {

	private File file;
	
	private List<String> listOfQuestions;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public List<String> getListOfQuestions() {
		return listOfQuestions;
	}

	public void setListOfQuestions(List<String> listOfQuestions) {
		this.listOfQuestions = listOfQuestions;
	}

	@Override
	public String toString() {
		return "FileBean [file=" + file + ", listOfQuestions=" + listOfQuestions + "]";
	}
	
	
	
}
