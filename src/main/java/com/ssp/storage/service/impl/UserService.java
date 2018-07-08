package com.ssp.storage.service.impl;

import java.util.Arrays;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ssp.storage.constant.ErrorCode;
import com.ssp.storage.domain.Folder;
import com.ssp.storage.domain.User;
import com.ssp.storage.domain.UserSecurityQuestion;
import com.ssp.storage.exception.UserException;
import com.ssp.storage.repository.FolderRepository;
import com.ssp.storage.repository.UserRepository;
import com.ssp.storage.service.IUserSecurityQuestionService;
import com.ssp.storage.service.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	IUserSecurityQuestionService userSecurityQuestionService;

	@Autowired
	FolderRepository folderRepository;

	@Value("${server.port}")
	private int port;

	@Override
	public boolean authenticate(String username, String password) throws UserException {
		return userRepository.existsByUsernameAndPassword(username, password);
	}

	@Transactional
	@Override
	public User addUser(User user) throws UserException {
		if (userRepository.existsByEmail(user.getEmail()))
			throw new UserException(port + ErrorCode.EMAIL_DUPLICATE.getKey(), ErrorCode.EMAIL_DUPLICATE.getValue());
		if (userRepository.existsByUsername(user.getUsername()))
			throw new UserException(port + ErrorCode.USERNAME_DUPLICATE.getKey(),
					ErrorCode.USERNAME_DUPLICATE.getValue());
		try {
			Folder folder = new Folder();
			folder.setUser(user);
			folder.setRoot(true);
			folder.setFolderName("root");
			folder.setLevel(0);
			user.setFolders(Arrays.asList(folder));
			User userResponse = userRepository.save(user);
			folder.setUser(userResponse);
			folderRepository.save(folder);
			/*for (UserSecurityQuestion userSecurityQuestion : user.getQuestions()) {
				userSecurityQuestion.setUser(user);
			}
			userSecurityQuestionService.addSecurityQuestions(user, user.getQuestions());*/
			return userResponse;
		} catch (DataAccessException e) {
			throw new UserException(port, e.getMessage());
		}
	}

}
