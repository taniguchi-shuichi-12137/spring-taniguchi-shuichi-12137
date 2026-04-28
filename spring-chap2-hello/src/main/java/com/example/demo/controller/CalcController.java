package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CalcController {
	
	
	/**
	 * 「/calc」
	 * @return
	 */
	
	@GetMapping("/calc")
	public String calc(
			Model model
			) {
		
		int num1 = 3;
		int num2 = 5;
		
	
		model.addAttribute( "result" , num1 + num2 ); 		
		//HTMLファイルの指定
		return"calc";
				
		
		
	}

}
