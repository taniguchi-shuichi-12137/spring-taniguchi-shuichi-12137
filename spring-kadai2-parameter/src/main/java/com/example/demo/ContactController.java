package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContactController {

	@GetMapping("/contact")
	public String index() {

		return "contactForm";

	}

	@PostMapping("/contact")
	public String contact(
			@RequestParam(name = "name") String name,
			@RequestParam(name = "email", defaultValue = "") String email,
			Model model) {

		List<String> errList = new ArrayList<>();

		if (name == null || name.isEmpty()) {

			errList.add("名前は必須です");

		}

		model.addAttribute("name", name);
		model.addAttribute("email", email);

		if (errList.size() > 0) {
			model.addAttribute("errList", errList);
			return "contactForm";
		}

		return "contactResult";
	}

}
