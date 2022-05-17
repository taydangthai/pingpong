package com.pingpong.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class PasswordEncoderTest {

	@Test
	public void testPasswordEncoder() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String s = "123456";
		String encode = encoder.encode(s);
		System.out.println(encode);
		
		boolean matches = encoder.matches(s, encode);
		assertThat(matches).isTrue();
	}
}
