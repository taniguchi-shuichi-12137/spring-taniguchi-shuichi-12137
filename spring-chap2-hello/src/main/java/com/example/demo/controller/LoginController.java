package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String index() {

		return "login";

		
		
	}
	@PostMapping("/login")
	public String login(
			//↓をメソッドの引数にの中に書くと、入力した値を受け取れる
			//@RequestParam(name="テキストボックス等の名前")データ型 Java上の変数名
			@RequestParam(name="userId")String userId,
			@RequestParam(name="password")String password,
			Model model
			) {
		
		model.addAttribute("name", userId);
		
		return"hello";
	}
	

}
