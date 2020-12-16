package com.github.springlink.basic.module.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema
public class CreateTokenReply {
	@Schema(description = "AccessToken")
	private String accessToken;

	@Schema(description = "AccessToken will be expired in seconds")
	private Long expiresIn;
}
