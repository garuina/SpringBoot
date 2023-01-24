package kr.co.ch07.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.co.ch07.service.User6Service;
import kr.co.ch07.vo.User6VO;


@Controller
public class User6Controller {
	
	@Autowired
	private User6Service service;
	
	@GetMapping("/user6/list")
	public String User6List(Model model) {
		
		List<User6VO> users = service.selectUser6s();
		model.addAttribute("users", users);
		return "/user6/list";
	}
	
	@GetMapping("/user6/register")
	public String User6Register() {
		return "/user6/register";
	}
	
	@PostMapping("/user6/register")
	public String User6Register(User6VO vo) {
		service.insertUser6(vo);
		return "redirect:/user6/list";
	}
	
	@GetMapping("/user6/modify")
	public String User6Modify(Model model ,String name) {
		User6VO user = service.selectUser6(name);
		model.addAttribute("user", user);
		return "/user6/modify";
	}
	
	@PostMapping("/user6/modify")
	public String User6Modify(User6VO vo) {
		service.updateUser6(vo);
		return "redirect:/user6/list";
	}
	
	@GetMapping("/user6/delete")
	public String delete(String name) {
		service.deleteUser6(name);
		return "redirect:/user6/list";
	}
	
	
}