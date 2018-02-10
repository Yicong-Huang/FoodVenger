package pers.yicong.foodvenger.service;

import pers.yicong.foodvenger.model.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
}
