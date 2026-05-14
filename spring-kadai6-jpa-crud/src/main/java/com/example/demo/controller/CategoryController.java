package com.example.demo.controller;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.repository.CategoryRepository;

@Controller
public class CategoryController {

	
	private final CategoryRepository categoryRepository;

	public CategoryController(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	@GetMapping("/categories")
	public String index(Model model) {
		
		// カテゴリー一覧を取得: 本来はID昇順で固定したい
		model.addAttribute("categoryList", categoryRepository.findAll(Sort.by(Sort.Direction.ASC, "id")));
		
		return "categories";
	}
	
	@GetMapping("/categories/add")
	public String create() {
		
		return "addCategory";
		
	}
	
	//@PostMapping()
	


	
}
