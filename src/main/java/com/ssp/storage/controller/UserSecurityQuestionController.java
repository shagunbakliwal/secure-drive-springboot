/*package com.ssp.storage.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssp.storage.domain.User;
import com.ssp.storage.domain.UserSecurityQuestion;
import com.ssp.storage.error.AcgError;
import com.ssp.storage.exception.UserException;
import com.ssp.storage.service.IUserSecurityQuestionService;
import com.ssp.storage.web.ResponseEntity;


@RestController
@RequestMapping("/api/user/security")
public class UserSecurityQuestionController {

	Logger logger = LoggerFactory.getLogger(UserSecurityQuestionController.class);

	@Value("${server.port}")
	private int port;

	@Autowired
	IUserSecurityQuestionService userSecurityQuestionService;

	@GetMapping
	public ResponseEntity<?> getQuestions(@RequestHeader(name = "username") String username,
			@RequestHeader(name = "password") String password) {
		try {
			return new ResponseEntity<>(userSecurityQuestionService.getQuestions(username, password), HttpStatus.OK);
		} catch (UserException e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(new AcgError(e.getErrorCode(), e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateSecurity(@RequestBody User user,
			@Valid @RequestBody List<UserSecurityQuestion> userSecurityQuestionsRequest) {
		try {
			return new ResponseEntity<>(
					userSecurityQuestionService.addSecurityQuestions(user, userSecurityQuestionsRequest),
					HttpStatus.OK);
		} catch (UserException e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(new AcgError(e.getErrorCode(), e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = "/validate", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> validateSecurity(@RequestHeader String username, @RequestHeader String password,
			@Valid @RequestBody List<UserSecurityQuestion> list) {
		try {
			return new ResponseEntity<>(
					userSecurityQuestionService.validate(username,password, list),
					HttpStatus.OK);
		} catch (UserException e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(new AcgError(e.getErrorCode(), e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
*/