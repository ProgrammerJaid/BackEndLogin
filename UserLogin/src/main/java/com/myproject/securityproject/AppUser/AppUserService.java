package com.myproject.securityproject.AppUser;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.myproject.securityproject.registration.token.ConfirmationToken;
import com.myproject.securityproject.registration.token.ConfirmationTokenService;

@Service
public class AppUserService implements UserDetailsService{	//finds the user
	
	private StudentRepo studentRepo;
	private final static String USER_NOT_FOUND = 
			"User with email %s not found.";
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ConfirmationTokenService tokenService; 
	
	@Autowired
	public AppUserService(StudentRepo studentRepo) {
		this.studentRepo = studentRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		return studentRepo.findByEmail(email).orElseThrow(()->
						new UsernameNotFoundException(String.format(USER_NOT_FOUND, email)));
	}

	public String signUpUser(AppUser appUser) throws IllegalAccessException {
		if(studentRepo.existsByEmail(appUser.getEmail()))
			throw new IllegalAccessException("Account already exists on this email.");
		appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
		studentRepo.save(appUser);
		
		//implementing email service.
		String token = UUID.randomUUID().toString();
		
		ConfirmationToken cToken = new ConfirmationToken(
				token,LocalDateTime.now(),
				LocalDateTime.now().plusMinutes(15),
				appUser);
			
		tokenService.saveConfirmationToken(cToken);
		
		return token;
	}

	public int enableAppUser(String email) {
		return studentRepo.enableAppUser(email);
	}
}
