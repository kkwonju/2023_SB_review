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
	public ResultData<Article> writeArticle(String title, String body){
		
		if(Ut.empty(title)) {
			return ResultData.from("F-1", "제목을 입력해주세요");
		}
		
		if(Ut.empty(body)) {
			return ResultData.from("F-2", "내용을 입력해주세요");
		}
		
		ResultData writeRd = articleService.writeArticle(title, body); 
		int id = (int) writeRd.getData1();
		Article article = articleService.getArticle(id);
		return ResultData.newData(writeRd, article);
	}
	
	@RequestMapping("/usr/article/showArticle")
	@ResponseBody
	public ResultData<Article> showArticle(int id){
		Article article = articleService.getArticle(id); 
		return ResultData.from("S-1", Ut.f("%d번 게시글", id), article);
	}
	
	@RequestMapping("/usr/article/list")
	@ResponseBody
	public ResultData<List> showList(){
		List<Article> articles = articleService.getArticles();
		return ResultData.from("S-1", "Article List", articles);
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
