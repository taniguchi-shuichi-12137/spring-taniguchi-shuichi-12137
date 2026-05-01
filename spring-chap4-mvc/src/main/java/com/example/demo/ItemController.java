package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Item;

@Controller
public class ItemController {

	@GetMapping("/registItem")
	public String resist(Model model) {
		model.addAttribute("item", new Item());

		return "registItem";

	}

	@PostMapping("/complete")
	public String complete(
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "price", defaultValue = "") Integer price,
			Model model) {

		//Itemクラスのオブジェクトを生成
		Item item = new Item();

		//入力した商品名と価格をItemオブジェクトに登録
		item.setName(name);
		item.setPrice(price);

		//次のHTMLで変数を使えるようにする
		model.addAttribute("item", item); //ItemオブジェクトでHTMLに使用する
		//model.addAttribute("name", name);
		//model.addAttribute("price", price );

		//エラーチェック
		//商品名
		//価格：/1円以上であること
		List<String> errList = new ArrayList<>();

		if (name == null || name.isEmpty()) {

			errList.add("商品名は必須です");
		}
		if (price == null) {

			errList.add("価格は必須です");
		} else if (price < 1) {

			errList.add("1円以上入力してください");
		}

		if (errList.size() > 0) {

			model.addAttribute("errList", errList);

			return "registItem";

		}
		return "completeRegistItem";
	}

}
