package com.github.springlink.basic.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/user")
@Tag(name = "user", description = "User Module")
public class UserController {
	@GetMapping("/list")
	@Operation(summary = "List users")
	public List<Object> list() {
		return new ArrayList<>();
	}
}
