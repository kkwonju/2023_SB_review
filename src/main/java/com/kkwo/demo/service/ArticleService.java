package com.kkwo.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kkwo.demo.vo.Article;

@Service
public class ArticleService {
	private int lastArticleId;
	private List<Article> articles;
	
	public ArticleService() {
		this.lastArticleId = 0;
		this.articles = new ArrayList<>();
		makeTestData();
	}
	
	private void makeTestData() {
		for(int i = 1; i <= 10; i++) {
			String title = "제목";
			String body = "내용";
			addArticle(title, body);
		}
	}
	
	private int addArticle(String title ,String body) {
		int id = lastArticleId + 1;
		Article article = new Article(id, title, body);
		articles.add(article);
		lastArticleId++;
		return id;
	}
	
	public Article getArticle(int id) {
		for(Article article : articles) {
			if(article.getId() == id) {
				return article;
			}
		}
		return null;
	}
	
	public void modifyArticle(Article article, String title, String body) {
		article.setTitle(title);
		article.setBody(body);
	}
	
	public void deleteArticle(Article article) {
		articles.remove(article);
	}
	
	/* 게시글 작성 */
	public int doWrite(String title, String body) {
		int id = addArticle(title, body);
		return id;
	}
	
	/* 게시글 */
	public Article showArticle(int id) {
		return getArticle(id);
	}
	
	/* 게시글 목록 */
	public List<Article> showList() {
		return articles;
	}
}
