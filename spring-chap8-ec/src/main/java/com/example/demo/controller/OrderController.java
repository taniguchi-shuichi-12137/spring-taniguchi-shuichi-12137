package com.example.demo.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Item;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderDetail;
import com.example.demo.model.Cart;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.repository.OrderRepository;

@Controller
public class OrderController {

	private final Cart cart;

	private final CustomerRepository customerRepository;

	private final OrderRepository orderRepository;

	private final OrderDetailRepository detailRepository;
	


	public OrderController(Cart cart, CustomerRepository customerRepository, OrderRepository orderRepository,
			OrderDetailRepository detailRepository) {
		this.cart = cart;
		this.customerRepository = customerRepository;
		this.orderRepository = orderRepository;
		this.detailRepository = detailRepository;
	}

	@GetMapping("/order")
	public String index() {

		//costomerForm.htmlを表示
		return "costomerForm";
	}

	//「確認画面へ」ボタンを押下した時の処理
	@PostMapping("/order/confirm")
	public String confirm(
			@RequestParam(name = "name") String name,
			@RequestParam(name = "addres") String addres,
			@RequestParam(name = "tel") String tel,
			@RequestParam(name = "email") String email,
			//HTMlで変数を使用できるようにする準備
			Model model) {

		//うけとった情報を次のHTMLで使えるようにする
		model.addAttribute("name", name);
		model.addAttribute("addres", addres);
		model.addAttribute("tel", tel);
		model.addAttribute("email", email);

		//確認画面(orderConfirm.html)を表示
		return "orderConfirm";

	}

	@PostMapping("/orderConfirm")
	public String order(
			//隠しぱらめーたで受け取る
			@RequestParam(name = "name") String name,
			@RequestParam(name = "addres") String addres,
			@RequestParam(name = "tel") String tel,
			@RequestParam(name = "email") String email) {

		//customerテーブルにデータを登録(顧客情報)=======================

		//customerのオブジェクトを生成
		Customer customer = new Customer();

		//値をオブジェクトに設定
		customer.setName(name);
		customer.setAddress(addres);
		customer.setTel(tel);
		customer.setEmail(email);

		//customerRepositoryを通じて、データを登録

		customer = customerRepository.save(customer);
 
		//ordersテーブルにデータを登録(一回の注文全体に関する情報)============================

		//Orderのオブジェクトを生成
		Order order = new Order();

		//値のオブジェクトを設定
		order.setCustomerId(customer.getId()); //顧客ID
		order.setOrderedOn(LocalDate.now());//注文日
		order.setTotalPrice(cart.getTotal());//カートの合計金額

		//リポジトリーをを通じてデータを登録
		orderRepository.save(order);
		
		//orderDetailテーブルにデータを登録(注文の詳細：どの商品を何回購入したか → 商品1種類につき、１データ)
		for(Item item : cart.getItemList()) {
			//OrderDetailのオブジェクトを生成
			OrderDetail detail = new OrderDetail();
			
			//値をオブジェクトに設定
			detail.setOrderId(order.getId());	//注文データのID
			detail.setItemId(item.getId());		//商品のID
			detail.setQuantity(item.getQuantity());	//商品の個数
			//リポジトリーを通じてデータを登録
			detailRepository.save(detail);
		}
		
		//注文完了画面(orderd.html)

		return "orderd";
	}
}
