package kr.co.ch07.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.co.ch07.service.User5Service;
import kr.co.ch07.vo.User5VO;

@Controller
public class User5Controller {
	
	@Autowired
	private User5Service service;
	
	@GetMapping("/user5/list")
	public String User5List(Model model) {
		
		List<User5VO> users = service.selectUser5s();
		model.addAttribute("users", users);
		return "/user5/list";
	}
	
	@GetMapping("/user5/register")
	public String User5Register() {
		return "/user5/register";
	}
	
	@PostMapping("/user5/register")
	public String User5Register(User5VO vo) {
		service.insertUser5(vo);
		return "redirect:/user5/list";
	}
	
	@GetMapping("/user5/modify")
	public String User5Modify(Model model ,String name) {
		User5VO user = service.selectUser5(name);
		model.addAttribute("user", user);
		return "/user5/modify";
	}
	
	@PostMapping("/user5/modify")
	public String User5Modify(User5VO vo) {
		service.updateUser5(vo);
		return "redirect:/user5/list";
	}
	
	@GetMapping("/user5/delete")
	public String delete(String name) {
		service.deleteUser5(name);
		return "redirect:/user5/list";
	}
	
	
}
