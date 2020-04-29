package com.authentication.respository;



import org.springframework.data.mongodb.repository.MongoRepository;

import com.authentication.models.Token;



public interface TokenRepository extends MongoRepository<Token, String>{
	
	 Token findByEmail(String email);  

}
