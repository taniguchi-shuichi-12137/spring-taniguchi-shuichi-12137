package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity					//DB接続に使用するクラスと宣言
@Table(name="items")	//DBのどのテーブルと紐づいているかを記述
public class Item { 

	
	//フィールド：DBのテーブルの項目と同一
	
	//主キー項目には@Idをつける(つけないとエラー)
	@Id
	private Integer id;
	
	//項目は@Columnでテーブルの項目とフィールドを紐づける
	@Column(name="category_id")    //DBの項目名(スネークケース)
	private Integer categoryId;		//Javaの変数名(キャメルケース)
	
	@Column(name="name")
	private String name;
	
	@Column(name="price")
	private Integer price;
	
	//デフォルトコンストラクタ:Entityクラスの場合、無いとエラー
	public Item() {
		
	}
	//getter / setter

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
	
}
