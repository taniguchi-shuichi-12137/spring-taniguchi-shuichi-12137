package com.example.demo.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Item;
import com.example.demo.model.Cart;
import com.example.demo.repository.ItemRepository;

@Controller
public class CartController {

	private final Cart cart;

	private final ItemRepository itemRepository;

	public CartController(Cart cart, ItemRepository itemRepository) {
		this.cart = cart;
		this.itemRepository = itemRepository;
	}

	@GetMapping("/cart")
	public String showCart() {

		return "cart";

	}

	@PostMapping("/addCart")
	public String addCart(
			@RequestParam(name = "itemId") Integer itemId) {

		//1.idを使って商品のデータを取得
		Optional<Item> itemData = itemRepository.findById(itemId);

		//2.データ取得に成功したら、カートに商品を追加
		if (!itemData.isEmpty()) {
			//商品に個数を設定
			Item item = itemData.get();

			item.setQuantity(1);

			//カートに追加
			cart.addItem(item);

		}

		//リダイレクトで商品一覧ページを表示
		return "redirect:/cart";
	}

}
