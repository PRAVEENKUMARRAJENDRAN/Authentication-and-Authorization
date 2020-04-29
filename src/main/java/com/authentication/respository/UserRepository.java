package com.authentication.respository;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.authentication.models.*;





public interface UserRepository extends MongoRepository<User, String> {
	  Optional<User> findByUsername(String username);   //find by username method
	  
	  User  findByEmail(String email);

	  Boolean existsByUsername(String username); //check whether the email and user is already in use or not in dbs.

	  Boolean existsByEmail(String email);
	}
