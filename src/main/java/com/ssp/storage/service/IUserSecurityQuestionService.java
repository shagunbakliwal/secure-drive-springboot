package com.ssp.storage.service;

import java.util.List;

import javax.validation.Valid;

import com.ssp.storage.domain.User;
import com.ssp.storage.domain.UserSecurityQuestion;
import com.ssp.storage.exception.UserException;

public interface IUserSecurityQuestionService {

	List<String> getQuestions(String username, String password) throws UserException;

	boolean addSecurityQuestions(User user, List<UserSecurityQuestion> userSecurityQuestionsRequest) throws UserException;

	boolean validate(String username, String password, @Valid List<UserSecurityQuestion> userSecurityQuestionsRequest) throws UserException;
}
