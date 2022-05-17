package com.pingpong.admin.user;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pingpong.admin.FileUploadUtil;
import com.pingpong.common.entity.Role;
import com.pingpong.common.entity.User;

@Controller
public class UserController {

	@Autowired
	public UserService userService;

	@GetMapping("/users")
	public String listUser(Model model) {
		List<User> listUsers = userService.listUsers();
		model.addAttribute("listUsers", listUsers);
		return "users";
	}

	@GetMapping("/new-user")
	public String newUser(Model model) {
		List<Role> listRoles = userService.listRoles();
		User user = new User();
		user.setEnabled(true);
		model.addAttribute("user", user);
		model.addAttribute("listRoles", listRoles);
		model.addAttribute("pageTitle", "Create new user");
		
		return "user_form";
	}

	@PostMapping("/save")
	public String saveUser(User user,@RequestParam("image") MultipartFile multipartFile,
			RedirectAttributes attributes) throws IOException {
		System.out.println(user);
		System.out.println(multipartFile.getOriginalFilename());
		if(!multipartFile.isEmpty()) {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			
			user.setPhotos(fileName);
			User saveUser = userService.newUser(user);
			
			String uploadDir = "user-photos/" + saveUser.getId();
			FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		}
		
		
//		userService.newUser(user);
		attributes.addFlashAttribute("message", "Success");
		
		return "redirect:/users";
	}

	
	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable(name = "id") Integer id, Model model, RedirectAttributes attributes) {
		try {
			userService.delete(id);
			attributes.addFlashAttribute("message", "The User Id " + id + " has been deleted Successfully");
			
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			attributes.addAttribute("message", e.getMessage());
		}
		return "redirect:../users";
	}
	@GetMapping("{id}")
	public String editUser(@PathVariable(name = "id") Integer id, Model model, RedirectAttributes attributes) {
		try {
			User user = userService.getId(id);
			List<Role> listRoles = userService.listRoles();
			model.addAttribute("user", user);
			model.addAttribute("listRoles", listRoles);
			model.addAttribute("pageTitle", "Edit user (ID: " + id + ")");

			return "user_form";
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			attributes.addAttribute("message", e.getMessage());
		}
		return "redirect:users";
	}
	
	@GetMapping("/user/{id}/enabled/{status}")
	public String updateStatus(@PathVariable("id") Integer id, @PathVariable("status") Boolean enabled, 
			RedirectAttributes attributes) {
		
		userService.updateStatus(id, enabled);
		String status = enabled ? "enabled" : "disable";
		String message = "the user " + id + " has been " + status; 
		attributes.addFlashAttribute("message", message);
		
		return "redirect:/users";
	}
}
