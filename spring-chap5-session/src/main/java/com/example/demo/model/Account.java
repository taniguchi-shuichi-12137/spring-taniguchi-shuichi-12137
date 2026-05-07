package com.example.demo.model;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component		//Controllerクラスなどで、セッションに接続してあるオブジェ区とを使えるようになる
@SessionScope		//このクラスのオブジェクトがセッションに保持されるようになる
public class Account {
//

private String name;

	public Account() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
