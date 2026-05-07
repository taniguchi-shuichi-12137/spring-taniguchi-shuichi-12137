package com.example.demo.model;

import java.time.LocalDateTime;

public class Post {
	
	//フィールド
	
	//投稿者（名前）
	private String name;
	
	//投稿時間（LocalDateTime）	
	private LocalDateTime createAt = LocalDateTime.now();
	
	//タイトル
	private String title;
	
	//内容
	private String content;
	

	//デフォルトコンストラクタ
	public Post() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
