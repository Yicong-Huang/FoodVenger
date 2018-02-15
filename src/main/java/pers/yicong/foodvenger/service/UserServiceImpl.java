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
	public void saveUser(Customer user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

}
