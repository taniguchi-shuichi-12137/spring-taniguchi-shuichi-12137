package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Item;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ItemRepository;

@Controller
public class ItemController {

	//リポジトリもフィールドで変数定義
	private final ItemRepository itemRepository;
	
	private final CategoryRepository categoryRepository;

	//コンストラクタインジェクションで使用できるようにする
	public ItemController(ItemRepository itemRepository, CategoryRepository categoryRepository) {
		this.itemRepository = itemRepository;
		this.categoryRepository = categoryRepository;
	}
	
	@GetMapping("/")
	public String index(
			//検索では絶対にdefaultValueが必須
			@RequestParam(name="categoryId", defaultValue = "") Integer categoryId,
			Model model) {
		
		List<Item>itemList = new ArrayList<>();
		//カテゴリーIDがnullじゃない場合
		if(categoryId != null) {
			//itemsテーブルからカテゴリーIDが○○のデータを取得
			itemList = itemRepository.findByCategoryId(categoryId);
		}
		//カテゴリーIDがnullの場合
		else {
			//itemsテーブルから全件取得
			itemList = itemRepository.findAll();
			
		}
		
		
//		
		
		//itemsテーブルからカテゴリーIDが1のデータを取得
		
		//取得したitemListをHTMLで使用できるようにする
		model.addAttribute("itemList", itemList);
		//カテゴリー一覧をHTMLで使用できるようにする
		model.addAttribute("categoryList", categoryRepository.findAll());
		
		
		return "item";

	}

	
	}

	


