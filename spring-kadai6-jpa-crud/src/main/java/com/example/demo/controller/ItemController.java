package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Item;
import com.example.demo.repository.ItemRepository;

@Controller
public class ItemController {
	private final ItemRepository itemRepository;

	public ItemController(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	@GetMapping("/items")
	public String index(Model model) {

		List<Item> itemList = itemRepository.findAll();

		model.addAttribute("itemList", itemList);

		return "items";
	}

	@GetMapping("/items/add")
	public String create() {

		return "addItem";

	}

	@PostMapping("/items/add")
	public String store(
			@RequestParam(name = "categoryId", defaultValue = "") Integer categoryId,
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "price", defaultValue = "") Integer price) {

		Item item = new Item();

		item.setCategoryId(categoryId);
		item.setName(name);
		item.setPrice(price);

		itemRepository.save(item);

		return "redirect:/items";
		//なぜ/itemsなのか24行目で指定しているから
	}

	@GetMapping("/edit/{id}")
	public String edit(
			@PathVariable(name = "id") Integer itemId,
			Model model) {

		Optional<Item> itemData = itemRepository.findById(itemId);
		if (itemData.isEmpty()) {
			
			return "redirect:/items";
			
		}
		
		model.addAttribute("item", itemData.get());
		
		return "editItem";

		
	}
	
	

}
