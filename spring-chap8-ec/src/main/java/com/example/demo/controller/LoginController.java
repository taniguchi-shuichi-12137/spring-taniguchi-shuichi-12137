package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Account;

@Controller
public class LoginController {
	
	private final Account account;
	
	private final HttpSession session;
	


	public LoginController(Account account, HttpSession session) {
		this.account = account;
		this.session = session;
	}
	
	@GetMapping({"/", "/login"})
	public String index() {
		//セッション情報を破棄(全部削除)
		session.invalidate();
		
		
		//ログイン画面を表示
		return "login";
	}
	
	@PostMapping("/login")
	public String login(
			
			@RequestParam(name="name") String name
			) {
		
		//入力された名前をセッションに保存
		account.setName(name);
		
		
		//商品一覧へリダイレクト
		return"redirect:/items";
	}
	
}
