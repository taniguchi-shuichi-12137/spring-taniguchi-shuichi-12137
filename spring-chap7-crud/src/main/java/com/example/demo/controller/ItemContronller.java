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
public class ItemContronller {
	//①itemRepositoryを使用できるようにする
	private final ItemRepository itemRepository;

	//②コンストラクタインジェクションを作る
	ItemContronller(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	//itemRepositoryを使用できるようにする

	@GetMapping("/")
	public String index(Model model) {

		//全件取得
		List<Item> itemList = itemRepository.findAll();
		//取得したデータをHTMLで使用できるようにする
		model.addAttribute("itemList", itemList);
		//item.htmlを表示
		return "item";
	}

	@GetMapping("/add")
	public String create() {

		return "addItem";
	}

	@PostMapping("/add")
	public String store(
			//入力したデータを受け取る
			@RequestParam(name = "categoryId", defaultValue = "") Integer categoryId,
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "price", defaultValue = "") Integer price) {

		//1.Itemのエンティティのオブジェクトを生成する
		Item item = new Item();

		//2.値をItemのオブジェクトに登録（idはnull）
		item.setCategoryId(categoryId);
		item.setName(name);
		item.setPrice(price);

		//3.リポジトリーを通じてDBに登録

		itemRepository.save(item);

		//しょりが終了したら、パス「/」にリダイレクト
		return "redirect:/";
	}

	//更新画面を表示
	@GetMapping("/edit/{id}")
	public String edit(
			@PathVariable(name = "id") Integer itemId,
			Model model) {

		//1.リポジトリーを通じて、商品のデータを取得(idが一致するデータを取得)
		//findByIdのデータ型はOptional<エンティティ>で返却される
		Optional<Item> itemData = itemRepository.findById(itemId);
		//2.データが取得できなかった場合、商品一覧画面へリダイレクト
		if (itemData.isEmpty()) {

			return "redirect:/";
		}

		//3.データが取得できたら、取得したデータをＨＴＭＬで使用できるようにする
		model.addAttribute("item", itemData.get());

		//4.更新画面(editItem.html)を表示

		return "editItem";
	}

	//更新処理
	@PostMapping("/edit/{id}")
	public String update(
			//URLのパスパラメータを受け取る
			@PathVariable(name = "id") Integer itemId,
			//入力したデータを受け取る
			@RequestParam(name = "categoryId", defaultValue = "") Integer categotyId,
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "price", defaultValue = "") Integer price) {

		//1.リポジトリーを通じて、商品のデータを取得(idが一致するデータを取得)
		//findByIdのデータ型はOptional<エンティティ>で返却される
		Optional<Item> itemData = itemRepository.findById(itemId);
		//2.データが取得できなかった場合、商品一覧画面へリダイレクト
		if (itemData.isEmpty()) {

			return "redirect:/";
		}

		//3.取得したデータからitemオブジェクトを取得
		Item item = itemData.get();

		//4.値をItemオブジェクトに登録(idはnullでない状態　※取得したデータにはidが入ってるから)
		item.setCategoryId(categotyId);
		item.setName(name);
		item.setPrice(price);

		//5.リポジトリーを通じてDBに更新(idがnull出ない場合、更新)

		itemRepository.save(item);

		//処理が終わったら一覧画面へリダイレクト

		return "redirect:/";
	}

	//削除処理
	@PostMapping("/delete/{id}")
	public String delete(
			//URLのパスパラメータを受け取る
			@PathVariable(name = "id") Integer itemId

	) {

		//1.リポジトリーを通じて、商品のデータを取得(idが一致するデータを取得)
		//  findByIdのデータ型はOptional<エンティティ>で返却される
		Optional<Item> itemData = itemRepository.findById(itemId);
		//2.データが取得できなかった場合、商品一覧画面へリダイレクト
		if (itemData.isEmpty()) {

			return "redirect:/";
		}

		//3.リポジトリーを通じて削除
		itemRepository.deleteById(itemId);

		//処理が終わったら一覧画面へリダイレクト	
		return "redirect:/";

	}

}
