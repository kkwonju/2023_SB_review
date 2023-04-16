package com.kkwo.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kkwo.demo.vo.Article;

@Controller
public class UsrArticleController {
	private int lastArticleId;
	List<Article> articles;
	
	public UsrArticleController() {
		this.lastArticleId = 0;
		articles = new ArrayList<>();
	}

	@RequestMapping("/usr/article/write")
	@ResponseBody
	public String write(String title, String body){
		int id = lastArticleId + 1;
		lastArticleId++;
		Article article = new Article(id, title, body);
		articles.add(article);
		return id + "번 글이 생성됌";
	}
	
	@RequestMapping("/usr/article/list")
	@ResponseBody
	public List<Article> showList(){
		return articles;
	}
}
