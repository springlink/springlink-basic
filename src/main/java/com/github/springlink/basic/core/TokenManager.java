package com.github.springlink.basic.core;

import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;

public class TokenManager implements OpaqueTokenIntrospector {

	@Override
	public OAuth2AuthenticatedPrincipal introspect(String token) {
		// TODO Auto-generated method stub
		return null;
	}

}
