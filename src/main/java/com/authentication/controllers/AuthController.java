package com.authentication.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
//mport org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.authentication.models.ERole;
import com.authentication.models.Role;
import com.authentication.models.Token;
import com.authentication.models.User;
import com.authentication.payload.request.ForgetpasswordRequest;
import com.authentication.payload.request.LoginRequest;
import com.authentication.payload.request.ResetpasswordRequest;
import com.authentication.payload.request.SignupRequest;
import com.authentication.payload.response.ForgetpasswordResponse;
import com.authentication.payload.response.JwtResponse;
import com.authentication.payload.response.MessageResponse;
import com.authentication.respository.RoleRepository;
import com.authentication.respository.TokenRepository;
import com.authentication.respository.UserRepository;
import com.authentication.security.jwt.JwtUtils;
import com.authentication.security.services.UserDetailsImpl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;




@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;
	
	final String secretKey = "praveen";
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	private JavaMailSender sender;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TokenRepository tokenRepository;

	@Autowired
	RoleRepository roleRepository;


	@Autowired
	JwtUtils jwtUtils;
    
	
	
	//Login
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		


		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		

		return ResponseEntity.ok(new JwtResponse( 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getInterest(),
												 userDetails.getInterestlist(),
												 userDetails.getFirstname(),
												 userDetails.getLastname(),
												 userDetails.getEmail(), 
												 roles));
	}
	
	//Reset password
	@RequestMapping(value = "/{email}", method = RequestMethod.PATCH)
	public ResponseEntity<?> resetpassword(@PathVariable("email") String email,@Valid @RequestBody ResetpasswordRequest resetpasswordRequest) {
		
		
		  User prod = userRepository.findByEmail(email);
			
		  if(resetpasswordRequest.getPassword()!= null)
	          prod.setPassword((encoder.encode(resetpasswordRequest.getPassword())));
		    userRepository.save(prod);
	    
		
		    return ResponseEntity.ok(new MessageResponse("Password updated successfully!"));
}

	
	
	//forgetpassword
	@PostMapping("/forgetpassword/{email}")
	public ResponseEntity<?> forgetpassword(@PathVariable("email") String email,@Valid @RequestBody ForgetpasswordRequest ForgetpasswordRequest) {
		
		Token prod = tokenRepository.findByEmail(email);
		String encryptedpassword= prod.getToken();
		
		
		
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		try {
			helper.setTo(email);
			helper.setText(encryptedpassword);
			helper.setSubject("Mail From Spring Boot");
		} catch (MessagingException e) {
			e.printStackTrace();
			return ResponseEntity.ok(new MessageResponse("Error while sending mail .."));
		}
		sender.send(message);
		
		return ResponseEntity.ok(new MessageResponse("tokensend"));
		
}
	
	


    //Register
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		
		
		
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}
		
		
	
		
		
		// String encryptedString = AES.encrypt(signUpRequest.getPassword(), secretKey) ;

		//Create new user's account
		User user = new User(signUpRequest.getUsername(),
				             signUpRequest.getFirstname(),
				             signUpRequest.getLastname(),
				             signUpRequest.getInterest(),
				             signUpRequest.getInterestlist(),
			                 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRoles();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_STUDENT)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "tutor":
					Role modRole = roleRepository.findByName(ERole.ROLE_TUTOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_STUDENT)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);
		
		
		
		//saving the token
		
		

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(signUpRequest.getUsername(), signUpRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
	
		
		    Token token =new Token(signUpRequest.getUsername(),signUpRequest.getEmail());
	        token.setToken(jwt);
		    tokenRepository.save(token);
		
		
		
		
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}
