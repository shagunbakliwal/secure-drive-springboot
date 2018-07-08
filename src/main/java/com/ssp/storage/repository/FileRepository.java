package com.ssp.storage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssp.storage.domain.File;

public interface FileRepository extends JpaRepository<File, Long> {

	public File findByFileNameAndFolderId(String fileName, Long id);
}
