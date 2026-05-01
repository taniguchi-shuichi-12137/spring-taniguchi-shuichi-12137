package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Account;

@Controller
public class AccountController {

	@GetMapping("/account")
	public String index(Model model) {
		model.addAttribute("account", new Account());

		return "accountForm";

	}

	@PostMapping("/account/confirm")
	public String comfirm(
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "email", defaultValue = "") String email,
			@RequestParam(name = "password", defaultValue = "") String password,
			Model model) {

		Account account = new Account();

		account.setName(name);
		account.setEmail(email);
		account.setPassword(password);

		model.addAttribute("account", account);

		List<String> errList = new ArrayList<>();

		if (name == null || name.isEmpty()) {
			errList.add("名前は必須です");

		} else if (name.length() > 21) {
			errList.add("名前は20文字以内で入力してください");
		}
		if (email.length() == 0) {
			errList.add("メールアドレスは必須です");
		}
		if (password.length() == 0) {
			errList.add("パスワードは必須です");
		}
		if (errList.size() > 0) {
			model.addAttribute("errList", errList);
			return "accountForm";
		}
		//model.addAttribute("name", name);
		//model.addAttribute("email", email);
		//model.addAttribute("password", password);
		model.addAttribute("account", account);
		return "accountConfirm";

	}
	
	@PostMapping("/account")
	public String store(
			@RequestParam(name ="name")String name
			
			
			) {
		
	}

}
