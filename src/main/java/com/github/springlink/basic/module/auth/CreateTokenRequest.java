package com.github.springlink.basic.module.auth;

import javax.validation.constraints.NotEmpty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema
public class CreateTokenRequest {
	@NotEmpty
	@Schema(description = "Username")
	private String username;

	@NotEmpty
	@Schema(description = "Password")
	private String password;
}
