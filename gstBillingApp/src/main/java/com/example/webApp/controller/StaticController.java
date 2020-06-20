package com.example.webApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StaticController {
	
	@RequestMapping(value = "/")
	public String home(Model model){
		model.addAttribute("condition", "false");
		return "index.html";
	}
	
	@RequestMapping(value = "/entry")
	public String entry(Model model){
		model.addAttribute("name","priyank");
		
		return "entry.html";
	}
	
	@RequestMapping(value = "/billing")
	public String bill(){
		//model.addAttribute("condition", "false");
		return "billing.html";
	}
	
//	@RequestMapping(value = "/redirect")
//	public String redirect(Model model){
//		return "entry.html";
//	}
}
