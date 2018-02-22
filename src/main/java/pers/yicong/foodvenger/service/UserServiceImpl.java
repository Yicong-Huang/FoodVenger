package pers.yicong.foodvenger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pers.yicong.foodvenger.model.Customer;
import pers.yicong.foodvenger.model.Role;
import pers.yicong.foodvenger.repository.RoleRepository;
import pers.yicong.foodvenger.repository.UserRepository;

import java.util.Arrays;
import java.util.HashSet;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public Customer findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
    public void saveCustomer(Customer customer) {
        customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
        customer.setActive(1);
        Role userRole = roleRepository.findByRole("ADMIN");
        customer.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        customer.setCcId(1);
//		customer.setSales(new HashSet<>());
        customer.setAddress("");
        userRepository.save(customer);
	}

}
