package com.nandhu.bookapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nandhu.bookapp.model.Book;
import com.nandhu.bookapp.repository.BookRepository;
@Controller
@RequestMapping("/books")
public class BookController {

	@Autowired
	BookRepository bookRepo;

	@GetMapping
	public String book_list(ModelMap modelMap) {
		List<Book> book = bookRepo.findAll();
		System.out.println(book);
		modelMap.addAttribute("books",book);
		return "booklist";
		}
	@GetMapping("/view/{id}")
	public String book_view(@PathVariable("id") Long id,ModelMap modelMap ){
	Book book=bookRepo.findOne(id);
	System.out.println(book);
	if(book!=null){
		modelMap.addAttribute("books",book);
		return "bookorder";
	}
		
	else
	{
		return "booklist";
	
}
}
}