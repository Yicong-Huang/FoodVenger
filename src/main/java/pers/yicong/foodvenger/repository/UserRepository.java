package pers.yicong.foodvenger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pers.yicong.foodvenger.model.Customer;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<Customer, Long> {
	Customer findByEmail(String email);
}
