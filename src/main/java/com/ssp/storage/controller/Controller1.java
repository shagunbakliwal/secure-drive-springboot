package com.ssp.storage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.ssp.storage.beans.FileBean;
import com.ssp.storage.constant.ErrorCode;
import com.ssp.storage.domain.User;
import com.ssp.storage.domain.UserSecurityQuestion;
import com.ssp.storage.error.AcgError;
import com.ssp.storage.exception.UserException;
import com.ssp.storage.service.IFilesService;
import com.ssp.storage.service.IFolderService;
import com.ssp.storage.service.IUserSecurityQuestionService;
import com.ssp.storage.service.IUserService;
import com.ssp.storage.service.impl.UserSecurityQuestionService;
import com.ssp.storage.vo.Signup;
import com.ssp.storage.web.ResponseEntity;

@Controller
@RequestMapping("/")
public class Controller1 {

	Logger logger = LoggerFactory.getLogger(Controller1.class);

	@Autowired
	IUserService userService;

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

	@PostMapping(value = "/signup")
	public String signup(@ModelAttribute Signup signup, Model model) {
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
			/*
			 * return new ResponseEntity<>(new AcgError(e.getErrorCode(), e.getMessage()),
			 * HttpStatus.INTERNAL_SERVER_ERROR);
			 */
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			/*
			 * return new ResponseEntity<>(e.getMessage(),
			 * HttpStatus.INTERNAL_SERVER_ERROR);
			 */
		}
		return "home";
	}

	@GetMapping
	public String e(Model model) {
		model.addAttribute("securityQuestions", userSecurityQuestionService.getSecurityQuestions());
		return "signup";
	}

	@Autowired
	IFilesService fileService;

	@PostMapping("/api/file/addFile")
	public ResponseEntity<?> addFile(@RequestBody MultipartFile file, @RequestHeader String parent,
			@RequestHeader String userName, @RequestHeader String absolutePath, @RequestHeader int level) {
		return new ResponseEntity<>(fileService.addFile(file, parent, userName, absolutePath, level), HttpStatus.OK);
	}

	@GetMapping("/api/file/getFile")
	public ResponseEntity<?> getFile(@RequestHeader String folder, @RequestHeader String parentFolder,
			@RequestHeader String userName, @RequestHeader String fileName, HttpServletResponse response,
			@RequestHeader String tracePath, @RequestHeader int level) {
		try {
			FileBean file = fileService.getFile(folder, parentFolder, userName, fileName, tracePath, level);
			if (file.getFile() != null) {
				response.setContentType("application/OCTET-STREAM");
				response.setHeader("Content-Disposition", "attachment; filename=" + file.getFile().getFileName());
				ServletOutputStream sos = response.getOutputStream();
				sos.write(file.getFile().getFile());
				sos.flush();
				return new ResponseEntity<>("Success", HttpStatus.OK);
			} else {
				return new ResponseEntity<>(file.getListOfQuestions(), HttpStatus.OK);
			}
		} catch (Exception e) {
			// return new ResponseEntity<>(e.getMessage(),
			// HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<>(
					new AcgError(ErrorCode.DOWNLOAD_FAILED.getKey(), ErrorCode.DOWNLOAD_FAILED.getValue()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		/*
		 * return new ResponseEntity<>( new AcgError(ErrorCode.DOWNLOAD_FAILED.getKey(),
		 * ErrorCode.DOWNLOAD_FAILED.getValue()), HttpStatus.INTERNAL_SERVER_ERROR);
		 */
	}

	@Autowired
	IFolderService folderFilesService;

	@RequestMapping(value = "/root", method = RequestMethod.GET)
	public String getRootFolder(@RequestHeader String userName, Model model) {
		System.out.println(folderFilesService.getRootFolder(userName));
		model.addAttribute("list", folderFilesService.getRootFolder(userName));
		return "home";
	}

	@GetMapping("/api/folder/addFolder")
	public ResponseEntity<?> addFolder(@RequestHeader String userName, @RequestHeader String folderName,
			@RequestHeader String parent, @RequestHeader int level) {
		return new ResponseEntity<>(folderFilesService.addFolder(userName, folderName, parent, level), HttpStatus.OK);
	}

	@GetMapping("/api/folder/getFolder")
	public ResponseEntity<?> getFolder(@RequestHeader String userName, @RequestHeader String folderName,
			@RequestHeader String parent, @RequestHeader int level) {
		return new ResponseEntity<>(folderFilesService.getFolder(userName, folderName, parent, level), HttpStatus.OK);
	}

	@Value("${server.port}")
	private int port;

	@Autowired
	UserSecurityQuestionService userSecurityQuestionService;

	@GetMapping("/api/user/security")
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

	@PostMapping(value = "/api/user/security/update", produces = MediaType.APPLICATION_JSON_VALUE)
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
			return new ResponseEntity<>(userSecurityQuestionService.validate(username, password, list), HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
