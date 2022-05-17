package com.pingpong.admin.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/check-email")
	public String checkDuplicateEmail(@Param("is") Integer id, @Param("email") String email) {
		return userService.isEmailUnique(id, email) ? "OK" : "Duplicated-trunglap";
	}
}
