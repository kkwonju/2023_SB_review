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
		makeTestData();
	}
	
	public void makeTestData() {
		for(int i = 1; i <= 10; i++) {
			String title = "제목";
			String body = "내용";
			doWrite(title, body);
		}
	}
	
	public int doWrite(String title ,String body) {
		int id = lastArticleId + 1;
		Article article = new Article(id, title, body);
		articles.add(article);
		lastArticleId++;
		return id;
	}
	
	@RequestMapping("/usr/article/write")
	@ResponseBody
	public String write(String title, String body){
		int id = doWrite(title, body);
		return id + "번 글이 생성됌";
	}
	
	@RequestMapping("/usr/article/list")
	@ResponseBody
	public List<Article> showList(){
		return articles;
	}
}
