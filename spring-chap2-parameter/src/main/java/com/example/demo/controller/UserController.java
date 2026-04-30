package com.example.demo.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

	@GetMapping("/inputUser")
	public String input() {

		return "inputUser";
	}

	@PostMapping("/userConfirm")
	public String confirm(
			@RequestParam(name = "name") String name,
			//整数が入力されていなくても処理がとまらないようにするなら
			//@RequestParamの（）の中に「defaultValue="値"」を入れる
			@RequestParam(name = "age", defaultValue = "") Integer age,
			@RequestParam(name = "companyName") String companyName,
			//日付はLocalDate
			@RequestParam(name = "hireDate", defaultValue = "") LocalDate hireDate,
			//ラジオボタンのデータ型は、ラジオボタンの	valueに設定したデータ型
			@RequestParam(name = "gender", defaultValue = "") Integer gender,
			//チェックボックスのデータ型はvalueに設定したデータ型の配列
			@RequestParam(name = "hobby", defaultValue = "") List<String> hobbyList,
			Model model) {

		//入力チェック（ヴァリテーションチェック）：（大抵）処理の最初に行う
		//											一つ一つの項目を順番に確認する
		//エラー文言を保存する変数を作成する
		List<String> errList = new ArrayList<>();

		//名前（文字列）のチェック
		if (name == null || name.isEmpty()) {
			//エラーの処理
			errList.add("名前の入力は必須です");

		}

		//年齢のチェック
		if (age == null) {
			//エラーの処理
			errList.add("年齢の入力は必須です");

		}

		else if (age < 0 || age > 130) {

			errList.add("正しい年齢を入力してください");
		}

		//受け取った内容を次のHTMLで使えるようにする
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		model.addAttribute("companyName", companyName);
		model.addAttribute("hireDate", hireDate);
		model.addAttribute("gender", gender);
		model.addAttribute("hobbyList", hobbyList);

		//エラーが存在する場合入力のHTML(inputUser)を表示する
		if (errList.size() > 0) {
			//エラー文言をHTMLで使えるようにする
			model.addAttribute("errList", errList);
			return "inputUser";
		}

		return "userConfirm";

	}

}
