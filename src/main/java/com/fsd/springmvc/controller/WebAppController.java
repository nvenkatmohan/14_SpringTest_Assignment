package com.fsd.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebAppController {

	
	@RequestMapping("/")
	public String welcomePage(Model model) {
		
		return "bsmanagerhome";
	}

	
	
	
}
