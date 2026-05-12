package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.example.demo.entity.Item;

@Component
@SessionScope
public class Cart {
	
	//フィールド：商品一覧
	List<Item> itemList = new ArrayList<Item>();
	
	//デフォルトコンストラクタ
	public Cart() {
		
	}

	public List<Item> getItemList() {
		return itemList;
	}
	//引数に指定した商品をカートに追加する
	
	public void addItem(Item item) {
		
		//itemListに同じ商品があるか確認
		Item existItem = null;
		for(Item cartItem : itemList) {
			//カート内の商品と追加詞よとしてる商品のIDが一致したら
			if(cartItem.getId() == item.getId()) {
				//変数に商品データを入れる
				existItem = cartItem;
				break;
			}
		}
		//同じ商品がない場合、itemListにitemを追加
		if(existItem == null) {
			//itemListにitemを追加
			itemList.add(item);
		}
		//同じ商品がある場合、個数を追加
		else {
			//個数を追加
			existItem.setQuantity(existItem.getQuantity() + item.getQuantity());
		}
	}

}
