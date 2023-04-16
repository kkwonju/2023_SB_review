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
	
	private void makeTestData() {
		for(int i = 1; i <= 10; i++) {
			String title = "제목";
			String body = "내용";
			doWrite(title, body);
		}
	}
	
	private int addArticle(String title ,String body) {
		int id = lastArticleId + 1;
		Article article = new Article(id, title, body);
		articles.add(article);
		lastArticleId++;
		return id;
	}
	
	private Article getArticle(int id) {
		for(Article article : articles) {
			if(article.getId() == id) {
				return article;
			}
		}
		return null;
	}
	
	private void modifyArticle(Article article, String title, String body) {
		article.setTitle(title);
		article.setBody(body);
	}
	
	private void deleteArticle(Article article) {
		articles.remove(article);
	}
	
	@RequestMapping("/usr/article/write")
	@ResponseBody
	public String doWrite(String title, String body){
		int id = addArticle(title, body);
		return id + "번 글이 생성됌";
	}
	
	@RequestMapping("/usr/article/article")
	@ResponseBody
	public Article showArticle(int id){
		return getArticle(id);
	}
	
	@RequestMapping("/usr/article/list")
	@ResponseBody
	public List<Article> showList(){
		return articles;
	}
	
	@RequestMapping("/usr/article/modify")
	@ResponseBody
	public Object doModify(int id, String title, String body){
		Article article = getArticle(id); 
		if(article == null) {
			return id + "번 글은 존재하지 않습니다";
		}
		modifyArticle(article, title, body);
		return article;
	}
	
	@RequestMapping("/usr/article/delete")
	@ResponseBody
	public String doDelete(int id){
		Article article = getArticle(id); 
		if(article == null) {
			return id + "번 글은 존재하지 않습니다";
		}
		deleteArticle(article);
		return id + "번 글을 삭제했습니다";
	}
}
