package com.ssp.storage.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ssp.storage.domain.User;
import com.ssp.storage.domain.UserSecurityQuestion;

public interface UserSecurityQuestionRepository extends CrudRepository<UserSecurityQuestion, Long> {

	boolean existsByUserUsername(String username);

	List<UserSecurityQuestion> findAllByUser(User user);

	List<UserSecurityQuestion> findAllByUserUsername(String username);

	void deleteByUser(User user);

}
