package kr.co.ch07.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.co.ch07.service.User2Service;
import kr.co.ch07.vo.User2VO;

@Controller
public class User2Controller {
	
	@Autowired
	private User2Service service;
	
	@GetMapping("/user2/list")
	public String User2List(Model model) {
		
		List<User2VO> users = service.selectUser2s();
		model.addAttribute("users", users);
		return "/user2/list";
	}
	
	@GetMapping("/user2/register")
	public String User2Register() {
		return "/user2/register";
	}
	
	@PostMapping("/user2/register")
	public String User2Register(User2VO vo) {
		service.insertUser2(vo);
		return "redirect:/user2/list";
	}
	
	
	
}
