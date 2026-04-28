package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//このクラス内のメソッドと、URLのパスを紐づけるために必要
@Controller
public class HelloController {
	
	//戻り値：文字列 空文字を返却
	//メソッド名：index
	//引数なし
	//修飾子 戻り値 メソッド（引数）{} 
	
	/**
	 * URLで、パスに/が指定された場合、
	 * このメソッドを使用する
	 * @GetMapping(パス)
	 * @return
	 */
	
	@GetMapping("/")
	public String index(
			Model model//HTMLに変数を適応する場合、必要になるもの
			){
		
		//HTMLの変数を定義
		//model.addAttribute("変数名", "値");
		model.addAttribute("name", "谷口");
		

		//returnで返却する値は、表示したいHTMLファイル名
		//hello.htmlを表示
		return"hello";
		
	}

}
