package com.ssp.storage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssp.storage.domain.Folder;

public interface FolderRepository extends JpaRepository<Folder, Long> {

	public Folder findByUserUsernameAndRootTrue(String userName);

	public Folder findByUserUsernameAndFolderNameAndLevel(String userName, String folderName, int level);

	public Folder findByUserUsernameAndFolderNameAndParentFolderNameAndLevel(String userName, String folderName,
			String parent, int level);
}
