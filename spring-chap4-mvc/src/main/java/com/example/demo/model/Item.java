package com.example.demo.model;

public class Item {

	private String name; //商品名
	private Integer price;//価格

	public Item() {
	}
	//引数ありのコンストラクタ
//	public Item(String name, Integer price) {
//	this.name = name;
//		this.price = price;
//	}

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
	
	//get○○がHTMLから呼ばれるという特性を利用した処理
	//${item.info}と記述すると、「イヤホン：1000円」が使用できるようになる
	public String getInfo() {
		return name + " : " + price + "円";
	}

}
