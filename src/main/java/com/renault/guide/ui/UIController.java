package com.renault.guide.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UIController {
	@GetMapping("/map")
	public String map() {
		return "index";
	}
}
