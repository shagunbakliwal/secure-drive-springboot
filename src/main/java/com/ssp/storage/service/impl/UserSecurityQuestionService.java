package com.ssp.storage.service.impl;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ssp.storage.constant.ErrorCode;
import com.ssp.storage.domain.User;
import com.ssp.storage.domain.UserSecurityQuestion;
import com.ssp.storage.exception.UserException;
import com.ssp.storage.repository.UserRepository;
import com.ssp.storage.repository.UserSecurityQuestionRepository;
import com.ssp.storage.service.IUserSecurityQuestionService;

@Service
public class UserSecurityQuestionService implements IUserSecurityQuestionService {

	@Autowired
	UserSecurityQuestionRepository userSecurityQuestionRepository;

	@Autowired
	UserRepository userRepository;

	@Value("${server.port}")
	private int port;
	private Set<String> securityQuestions = new TreeSet<>();

	public Set<String> getSecurityQuestions() {
		return securityQuestions;
	}

	public void setSecurityQuestions(Set<String> securityQuestions) {
		this.securityQuestions = securityQuestions;
	}

	@PostConstruct
	public void setUp() {
		securityQuestions.add("What time of the day were you born?");
		securityQuestions.add("What is the middle name of your youngest cousin?");
		securityQuestions.add("What was the last name of your first grade teacher");
	}

	@Override
	public List<String> getQuestions(String username, String password) throws UserException {
		if (!userRepository.existsByUsernameAndPassword(username, password))
			throw new UserException(port + ErrorCode.INVALID_CREDENTIALS.getKey(),
					ErrorCode.INVALID_CREDENTIALS.getValue());
		return userSecurityQuestionRepository.findAllByUserUsername(username).stream().map(a -> a.getQuestion())
				.collect(Collectors.toList());
	}

	@Transactional
	@Override
	public boolean addSecurityQuestions(User user, List<UserSecurityQuestion> userSecurityQuestionsRequest)
			throws UserException {
		/*
		 * if (!userRepository.existsByUsernameAndPassword(username, password)) throw
		 * new UserException(port + ErrorCode.INVALID_CREDENTIALS.getKey(),
		 * ErrorCode.INVALID_CREDENTIALS.getValue()); User user =
		 * userRepository.findByUsername(username); for (UserSecurityQuestion
		 * userSecurityQuestion : userSecurityQuestionsRequest) {
		 * userSecurityQuestion.setUser(user); }
		 */
		try {
			userSecurityQuestionRepository.deleteByUser(user);
			userSecurityQuestionRepository.saveAll(userSecurityQuestionsRequest);
			return true;
		} catch (DataAccessException e) {
			throw new UserException(port, e.getMessage());
		}
	}

	public boolean validate(String username, String password, List<UserSecurityQuestion> requestList) {
		User user = userRepository.findByUsernameAndPassword(username, password);
		List<UserSecurityQuestion> list = userSecurityQuestionRepository.findAllByUser(user);
		for (UserSecurityQuestion userSecurityQuestion : list) {
			for (UserSecurityQuestion requestSecurityQuestion : requestList) {
				if (requestSecurityQuestion.getQuestion().equals(userSecurityQuestion.getQuestion())) {
					if (!userSecurityQuestion.getAnswer().equals(requestSecurityQuestion.getAnswer())) {
						return false;
					}
				}
			}
		}
		return true;
	}
}