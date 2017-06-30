package com.nandhu.bookapp.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nandhu.bookapp.model.User;
import com.nandhu.bookapp.repository.BookRepository;
import com.nandhu.bookapp.repository.UserRepository;
import com.nandhu.bookapp.service.UserService;
import com.nandhu.bookapp.util.RegistrationForm;
@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserRepository userRepo;
    BookRepository bookRepo;
    UserService userService;
	@GetMapping("/register")
	public String login() {
		return "register";
	}


	@PostMapping("/save")
	public String add_user(@ModelAttribute @Valid RegistrationForm user,BindingResult result,ModelMap modelMap,HttpSession session)throws Exception {
	try
	{
		System.out.println("Regsiter"+user);
		if(result.hasErrors())
		{
			modelMap.addAttribute("ERROR_REG",result.getAllErrors());
		}
		else
		{
			userService.save(user);
		}
		return "login";
	}
	catch(Exception e)
	{
		return "redirect:../" ;
	}
	}

	@GetMapping("/login")
	public String login_user() {
		return "login";
	}

	@PostMapping("/validate")
	public String authorize(@RequestParam("email") String email, @RequestParam("password") String password,HttpSession session) {
		User u = userRepo.findByEmailAndPassword(email, password);
		if (u != null) {
			session.setAttribute("USER",u);
			return "redirect:../books/";

		} else {
			return "login";
		}

	}

}