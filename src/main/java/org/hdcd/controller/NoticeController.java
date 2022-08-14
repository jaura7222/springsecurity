package org.hdcd.controller;

import org.hdcd.domain.Notice;
import org.hdcd.service.NoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/notice")
public class NoticeController {
	
	
	private final NoticeService service;
	
	@GetMapping("/register")
	public void registerForm(Model model) throws Exception{
		Notice notice = new Notice();
		model.addAttribute(notice);
		
	}
	
	

	@PostMapping("/register")
	public String register(Notice notice, RedirectAttributes rttr) throws Exception{
		service.register(notice);
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/notice/list";
	}
	
	
	
	@GetMapping("/list")
	public void list(Model model) throws Exception{
		model.addAttribute("list", service.list());
	}
	
	
	@GetMapping("/read")
	public void read(int noticeNo, Model model) throws Exception{
		model.addAttribute(service.read(noticeNo));
	}
	
	
	@PostMapping("/remove")
	public String remove(int noticeNo, RedirectAttributes rttr) throws Exception{
		service.remove(noticeNo);
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/notice/list";
	}
	
	
	@GetMapping("/modify")
	public void modifyForm(int noticeNo, Model model) throws Exception{
		model.addAttribute(service.read(noticeNo));
	}
	
	
	@PostMapping("/modify")
	public String modify(Notice notice, RedirectAttributes rttr) throws Exception{
		service.modify(notice);
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/notice/list";
	}
	
	
	
	
	

}
