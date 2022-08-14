package org.hdcd.controller;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/*
	 * @RequestMapping(value="/", method = RequestMethod.GET) public String
	 * home(Locale locale, Model model) {
	 * logger.info(" welcome home!! the client locale is {} ", locale);
	 * 
	 * Date date = new Date(); DateFormat dateformat =
	 * DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
	 * String formattedDate = dateformat.format(date);
	 * model.addAttribute("serverTime", formattedDate);
	 * 
	 * return "Home";
	 * 
	 * 
	 * }
	 */
	
	
	@GetMapping(value="/")
	public String home(Locale locale, Model model) {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy 년 M월 d일 (E) a h시 m분 s초");
		String formattedNow = now.format(formatter);
		
		model.addAttribute("serverTime", formattedNow);
		return "home";
	}
	
	
	
	

}
