package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Controller
public class UserController {
	
	private final UserRepository userRepository;
	private Optional<User> userData;

	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@GetMapping("/users")
	public String index(Model model) {

		List<User> userList = userRepository.findAll();

		model.addAttribute("userList", userList);

		return "users";
	}
	
	@GetMapping("/users/add")
	public String create() {

		return "addUsers";
	

	}
	
	@PostMapping("/users/add")
	public String store(
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "email", defaultValue = "") String email,
			@RequestParam(name = "password", defaultValue = "") String password) {

		User user = new User();

		user.setName(name);
		user.setEmail(email);
		user.setPassword(password);

		userRepository.save(user);

		return "redirect:/users";
	
	
	
		}
	
	@GetMapping("/edit/{id}")
	public String edit(
			@PathVariable(name = "id") Integer userId,
			Model model) {

		Optional<User> userData = userRepository.findById(userId);
		if (userData.isEmpty()) {
			
			return "redirect:/user";
			
		}
		
		model.addAttribute("user", userData.get());
		
		return "editUser";

		
	}
	
	@PostMapping("/user/{id}/edit")
	public String update(
			@PathVariable(name="id") Integer userId,
			@RequestParam(name = "name", defaultValue = "")  String name,
			@RequestParam(name = "email", defaultValue = "") String email,
			@RequestParam(name = "password", defaultValue = "") String password) {
		
		Optional<User> userData = userRepository.findById(userId);


	return "redirect:/user";
	
	
}
	
	User user = userData.get();
	
	item.setName(name);
	item.setEmail(email);
	item.setpassword(password);
	
	itemRepository.save(item);

	
}
@PostMapping("/user/{id}/delete")
public String delete(@PathVariable(name="id") Integer itemId) {
	
	
	Optional<User> itemData = userRepository.findById(itemId);
	
	
	if(!userData.isEmpty()) {
		
		
		userRepository.deleteById(userId);
		
		
		
	}
	return "redirect:/user";
}






}
}





