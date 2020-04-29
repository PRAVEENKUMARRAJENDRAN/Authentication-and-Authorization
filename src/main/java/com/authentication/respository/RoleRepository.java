package com.authentication.respository;


import java.util.Optional;
import com.authentication.models.*;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface RoleRepository extends MongoRepository<Role, String> {
	  Optional<Role> findByName(ERole name);   // a optional method to find the role is present or not.
	}
