package com.ssp.storage.service;

import com.ssp.storage.domain.User;
import com.ssp.storage.exception.UserException;

public interface IUserService {
	public boolean authenticate(String username, String password) throws UserException;

	public User addUser(User acgUser) throws UserException;
}
