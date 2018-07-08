package com.ssp.storage.repository;

import org.springframework.data.repository.CrudRepository;

import com.ssp.storage.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {

	boolean existsByUsernameAndPassword(String username, String password);

	boolean existsByEmail(String email);

	boolean existsByUsername(String username);

	User findByUsername(String username);

	User findByUsernameAndPassword(String username, String password);

}
