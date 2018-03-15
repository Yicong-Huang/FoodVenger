package pers.yicong.foodvenger.service;

import pers.yicong.foodvenger.model.Customer;

public interface UserService {
	Customer findUserByEmail(String email);

	void saveCustomer(Customer customer);

	Customer findUserByEmailAndPassword(String email, String password);
}
