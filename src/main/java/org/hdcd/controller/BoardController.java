package org.hdcd.controller;

import java.security.Principal;
import java.util.Collection;

import org.hdcd.common.security.domain.CustomUser;
import org.hdcd.domain.Board;
import org.hdcd.domain.Member;
import org.hdcd.service.BoardService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
public class BoardController {
	
	
	private final BoardService service;
	
	
	//로그인 사용자 정보 사용
	
	@GetMapping("/register")
	public void registerForm(Model model) throws Exception {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		String username = authentication.getName();
		
		log.info("registerForm username = "  + username);
		
		Object principal = authentication.getPrincipal();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		
		log.info("registerForm authorities = "  + authorities);
		
		CustomUser cu = (CustomUser)principal;
		Member member = cu.getMember();
		
		Board board = new Board();
		board.setWriter(member.getUserId());
		model.addAttribute(board);
		
	}
	
	
	/*
	@GetMapping("/register")
	public void registerForm(Model model, @AuthenticationPrincipal CustomUser customUser) throws Exception {
		Member member = customUser.getMember();
		log.info("registerForm member.getUserId() = " + member.getUserId());
		
		Board board = new Board();
		board.setWriter(member.getUserId());
		model.addAttribute(board);
		
	}
	*/
	
	/*
	@GetMapping("/register")
	public void registerForm(Model model, Principal principal) throws Exception {
		
		log.info("registerForm principal.getName() = " + principal.getName());
		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken)principal;
		CustomUser customUser = (CustomUser)token.getPrincipal();
		Member member = customUser.getMember();
		
		Board board = new Board();
		board.setWriter(member.getUserId());
		model.addAttribute(board);
		
	}
	*/
	
	/*
	@GetMapping("/register")
	public void registerForm(Model model, Authentication authentication) throws Exception {
		
		CustomUser customUser = (CustomUser) authentication.getPrincipal();
		Member member = customUser.getMember();
		
		Board board = new Board();
		board.setWriter(member.getUserId());
		model.addAttribute(board);
		
	}
	*/
	
	@PostMapping("/register")
	public String register(Board board, RedirectAttributes rttr) throws Exception{
		service.register(board);
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/board/list";
	}
	
	
	
	@GetMapping("/list")
	public void list(Model model) throws Exception{
		model.addAttribute("list", service.list());
	}
	
	
	@GetMapping("/read")
	public void read(int boardNo, Model model) throws Exception{
		model.addAttribute(service.read(boardNo));
	}
	
	
	@PostMapping("/remove")
	public String remove(int boardNo, RedirectAttributes rttr) throws Exception{
		service.remove(boardNo);
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/board/list";
	}
	
	
	@GetMapping("/modify")
	public void modifyForm(int boardNo, Model model) throws Exception{
		model.addAttribute(service.read(boardNo));
	}
	
	
	@PostMapping("/modify")
	public String modify(Board board, RedirectAttributes rttr) throws Exception{
		service.modify(board);
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/board/list";
	}
	
	
	
	
	
}
