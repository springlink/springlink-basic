package com.github.springlink.basic.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.OAuth2IntrospectionAuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.OAuth2IntrospectionClaimNames;
import org.springframework.security.oauth2.server.resource.introspection.OAuth2IntrospectionException;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
public class AccessTokenManager implements OpaqueTokenIntrospector {
	private final UserDetailsService userDetailsService;

	private final Cache<String, CacheValue> tokenCache = CacheBuilder.newBuilder()
			.expireAfterWrite(12, TimeUnit.HOURS)
			.build();

	@Override
	public OAuth2AuthenticatedPrincipal introspect(String token) {
		CacheValue value = Optional.ofNullable(tokenCache.getIfPresent(token))
				.orElseThrow(() -> new OAuth2IntrospectionException("AccessToken has invalid or expired"));
		OAuth2AccessToken accessToken = value.getAccessToken();
		UserDetails userDetails = value.getUserDetails();

		Map<String, Object> attrs = new HashMap<>();
		attrs.put(OAuth2IntrospectionClaimNames.USERNAME, userDetails.getUsername());
		attrs.put(OAuth2IntrospectionClaimNames.ISSUED_AT, accessToken.getIssuedAt());
		attrs.put(OAuth2IntrospectionClaimNames.EXPIRES_AT, accessToken.getExpiresAt());
		attrs.put(OAuth2IntrospectionClaimNames.SCOPE, accessToken.getScopes());
		List<GrantedAuthority> authorities = accessToken.getScopes().stream()
				.map(s -> new SimpleGrantedAuthority("ROLE_" + s))
				.collect(Collectors.toList());
		return new OAuth2IntrospectionAuthenticatedPrincipal(attrs, authorities);
	}

	@Value
	private static class CacheValue {
		private final OAuth2AccessToken accessToken;
		private final UserDetails userDetails;
	}

}
