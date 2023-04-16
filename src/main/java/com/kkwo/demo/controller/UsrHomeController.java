package com.kkwo.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.AllArgsConstructor;
import lombok.Data;

@Controller
public class UsrHomeController {
	List<Article> articles = new ArrayList<>();
	
	@RequestMapping("/usr/home/main")
	@ResponseBody
	public String showMain() {
		return "안녕하세요";
	}

	@RequestMapping("/usr/home/showString")
	@ResponseBody
	public String showString() {
		return "String";
	}
	
	@RequestMapping("/usr/home/showChar")
	@ResponseBody
	public char showChar() {
		return 'C';
	}
	
	@RequestMapping("/usr/home/showInt")
	@ResponseBody
	public int showInt() {
		return 1;
	}
	
	@RequestMapping("/usr/home/showFloat")
	@ResponseBody
	public float showFloat() {
		return 333.333f;
	}
	
	@RequestMapping("/usr/home/showDouble")
	@ResponseBody
	public double showDouble() {
		return 333.333;
	}
	
	@RequestMapping("/usr/home/showBoolean")
	@ResponseBody
	public boolean showBoolean() {
		return  1 == 2;
	}
	
	@RequestMapping("/usr/home/showMap")
	@ResponseBody
	public Map<String, Object> showMap() {
		Map<String, Object> map = new HashMap<>();
		map.put("first", 1);
		map.put("second", "둘");
		return map;
	}
	
	@RequestMapping("/usr/home/showArticle")
	@ResponseBody
	public Article showArticle() {
		Article article = new Article(1, "제목");
		articles.add(article);
		return article;
	}
	
	
	@RequestMapping("/usr/home/showArray")
	@ResponseBody
	public String[] showArray() {
		String[] arr = new String[3];
		arr[0] = "일";
		arr[1] = "이";
		arr[2] = "삼";
		return arr;
	}

	@RequestMapping("/usr/home/showList")
	@ResponseBody
	public List<Article> showList() {
		return articles;
	}

}

@Data
@AllArgsConstructor
class Article {
	int id;
	String title;
}