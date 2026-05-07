package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Account;
import com.example.demo.model.Post;
import com.example.demo.model.PostList;

@Controller
public class BlogController {

	//フィールド：セッション用のフィールドを追加
	private final Account account;

	private final HttpSession session;
	
	private final PostList postlist;
	

	//コンストラクタインジェクション：セッション用のクラスを使えるようにする
	
	public Account getAccount() {
		return account;
	}

	public BlogController(Account account, HttpSession session, PostList postlist) {
		this.account = account;
		this.session = session;
		this.postlist = postlist;
	}

	public HttpSession getSession() {
		return session;
	}

	public PostList getPostlist() {
		return postlist;
	}

	//Mappingの()の中に{}を入れると、複数記述できる
	@GetMapping({ "/", "/logout" })
	public String index() {
		
		//セッションの情報を全削除
		session.invalidate();
		
		return "login";
	}

	@PostMapping("/login")
	public String login(
			@RequestParam(name = "name", defaultValue = "") String name,
			Model model) {

		//セッション用のくらすAccountのnameフィールドに値を保管する
		account.setName(name);

		//	model.addAttribute(name, name);
		//リダイレクト
		//処理が終了したら、Getmapping("/blog")に処理が移動する
		//ついでにURLも変わる
		//redirect:パス
		return "redirect:/blog";

	}

	@GetMapping("/blog")
	public String showBlog() {

		return "blog";
	}
	
	@PostMapping("/blog")
	public String post(
			//投稿した内容を受け取る
			@RequestParam(name="title") String title,
			@RequestParam(name="content") String content
			) {
		//ポストクラスのオブジェクトを生成
		Post post = new Post();
		
		//投稿者登録：セッションのの名前を登録
		
		
		//タイトルと内容を登録
		post.setTitle(title);
		post.setContent(content);
		
		//セッションの投稿一覧にPostオブジェくとを追加
		postlist.getList().add(post);
		
		
		return "blog";
		
	}



}
