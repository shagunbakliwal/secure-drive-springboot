/*package com.ssp.storage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssp.storage.domain.User;
import com.ssp.storage.domain.UserSecurityQuestion;
import com.ssp.storage.error.AcgError;
import com.ssp.storage.exception.UserException;
import com.ssp.storage.service.IUserService;
import com.ssp.storage.service.impl.UserSecurityQuestionService;
import com.ssp.storage.vo.Signup;
import com.ssp.storage.web.ResponseEntity;

@Controller
@RequestMapping("/q")
public class UserController {

	Logger logger = LoggerFactory.getLogger(UserController.class);

	@Value("${server.port}")
	private int port;

	@Autowired
	IUserService userService;

	@Autowired
	UserSecurityQuestionService userSecurityQuestionService;

	@GetMapping("/user")
	public ResponseEntity<?> authenticateUser(@RequestHeader(name = "username") String username,
			@RequestHeader(name = "password") String password) {
		try {
			return new ResponseEntity<>(userService.authenticate(username, password), HttpStatus.OK);
		} catch (UserException e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(new AcgError(e.getErrorCode(), e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "/signup"  , consumes= {"application/x-www-form-urlencoded;charset=UTF-8"} )
	public String signup(@ModelAttribute Signup signup, Model model) {
		System.out.println(signup);
		try {
			User user = new User();
			user.setUsername(signup.getUsername());
			user.setPassword(signup.getPassword());
			user.setEmail(signup.getEmail());
			user.setContactNumber(signup.getContactNumber());
			Iterator questions = userSecurityQuestionService.getSecurityQuestions().iterator();
			List<UserSecurityQuestion> userSecurityQuestions = new ArrayList<>();
			UserSecurityQuestion userSecurityQuestion = new UserSecurityQuestion();
			userSecurityQuestion.setQuestion(String.valueOf(questions.next()));
			userSecurityQuestion.setAnswer(signup.getAnswer1());
			userSecurityQuestion.setUser(user);
			userSecurityQuestions.add(userSecurityQuestion);

			userSecurityQuestion = new UserSecurityQuestion();
			userSecurityQuestion.setQuestion(String.valueOf(questions.next()));
			userSecurityQuestion.setAnswer(signup.getAnswer2());
			userSecurityQuestion.setUser(user);
			userSecurityQuestions.add(userSecurityQuestion);

			userSecurityQuestion = new UserSecurityQuestion();
			userSecurityQuestion.setQuestion(String.valueOf(questions.next()));
			userSecurityQuestion.setAnswer(signup.getAnswer3());
			userSecurityQuestion.setUser(user);
			userSecurityQuestions.add(userSecurityQuestion);

			user.setQuestions(userSecurityQuestions);
			userService.addUser(user);
			model.addAttribute("username", user.getUsername());
		} catch (UserException e) {
			logger.error(e.getMessage(), e);
			
			 * return new ResponseEntity<>(new AcgError(e.getErrorCode(), e.getMessage()),
			 * HttpStatus.INTERNAL_SERVER_ERROR);
			 
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			
			 * return new ResponseEntity<>(e.getMessage(),
			 * HttpStatus.INTERNAL_SERVER_ERROR);
			 
		}
		return "home";
	}

	@GetMapping
	public String e(Model model) {
		model.addAttribute("securityQuestions", userSecurityQuestionService.getSecurityQuestions());
		return "signup";
	}
}
*/