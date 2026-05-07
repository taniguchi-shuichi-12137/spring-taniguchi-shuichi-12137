package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;


@Component
@SessionScope
public class PostList {
	
	//フィールド
	//投稿一覧
	private List<Post> list = new ArrayList<>();
	
	//デフォルトコンストラクタ
	public PostList() {
		
	}

	public List<Post> getList() {
		return list;
	}

	public void setList(List<Post> list) {
		this.list = list;
	}
}
