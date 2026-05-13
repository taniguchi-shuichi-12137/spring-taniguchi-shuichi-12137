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
	
	/**
	 * 合計金額を算出して返却する処理
	 * @return
	 */
	public Integer getTotal() {
		//保存用変数totalを定義(初期値は0)
		Integer total = 0;
		
		//itemListを拡張for文で回して、合計金額を算出
		for(Item item : itemList) {
			//商品の小計(価格と個数の掛け算)をtotalに足し込む
			total += item.getPrice() * item.getQuantity();
		}
		
		//算出した合計金額を返却
		return total;
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
