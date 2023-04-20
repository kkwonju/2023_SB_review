package com.kkwo.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kkwo.demo.service.ArticleService;
import com.kkwo.demo.util.Ut;
import com.kkwo.demo.vo.Article;
import com.kkwo.demo.vo.ResultData;

@Controller
public class UsrArticleController {
	@Autowired
	private ArticleService articleService;
	
	@RequestMapping("/usr/article/write")
	@ResponseBody
	public String writeArticle(String title, String body){
		int id = articleService.writeArticle(title, body);
		return id + "번 글이 생성됌";
	}
	
	@RequestMapping("/usr/article/showArticle")
	@ResponseBody
	public ResultData showArticle(int id){
		Article article = articleService.getArticle(id); 
		return ResultData.from("S-1", Ut.f("%d번 게시글", id), article);
	}
	
	@RequestMapping("/usr/article/list")
	@ResponseBody
	public List<Article> showList(){
		return articleService.getArticles();
	}
	
	@RequestMapping("/usr/article/modify")
	@ResponseBody
	public Object doModify(int id, String title, String body){
		Article article = articleService.getArticle(id); 
		if(article == null) {
			return id + "번 글은 존재하지 않습니다";
		}
		articleService.modifyArticle(id, title, body);
		return article;
	}
	
	@RequestMapping("/usr/article/delete")
	@ResponseBody
	public String doDelete(int id){
		Article article = articleService.getArticle(id); 
		if(article == null) {
			return id + "번 글은 존재하지 않습니다";
		}
		articleService.deleteArticle(id);
		return id + "번 글을 삭제했습니다";
	}
}
