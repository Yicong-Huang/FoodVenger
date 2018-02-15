package pers.yicong.foodvenger.service;

import pers.yicong.foodvenger.model.Customer;

public interface UserService {
	Customer findUserByEmail(String email);

	void saveUser(Customer user);
}
