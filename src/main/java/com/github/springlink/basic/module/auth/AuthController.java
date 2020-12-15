package com.github.springlink.basic.module.auth;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.springlink.basic.core.AccessTokenManager;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
	private final AccessTokenManager accessTokenManager;
	
	@PostMapping("/access_token")
	public AccessTokenReply accessToken(@RequestBody @Valid AccessTokenRequest req) {
		
	}
}
