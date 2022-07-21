package com.myproject.securityproject.registration.token;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfirmationTokenService {

	private TokenRepo tokenRepo;

	@Autowired
	public ConfirmationTokenService(TokenRepo tokenRepo) {
		this.tokenRepo = tokenRepo;
	}
	
	public void saveConfirmationToken(ConfirmationToken token) {
		tokenRepo.save(token);
	}

	public ConfirmationToken getToken(String token) {
		ConfirmationToken cToken = tokenRepo.findByToken(token)
					.orElseThrow(()->new RuntimeException("Token not found"));
		return cToken;
	}

    public int setConfirmedAt(String token) {
        return tokenRepo.updateConfirmedAt(
                token, LocalDateTime.now());
    }
}
