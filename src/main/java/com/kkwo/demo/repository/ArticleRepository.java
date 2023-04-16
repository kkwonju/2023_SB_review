package com.kkwo.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.kkwo.demo.vo.Article;

@Component
public class ArticleRepository {
	private int lastArticleId;
	private List<Article> articles;
	
	public ArticleRepository() {
		this.lastArticleId = 0;
		this.articles = new ArrayList<>();
	}
	
	public void makeTestData() {
		for(int i = 1; i <= 10; i++) {
			String title = "제목";
			String body = "내용";
			addArticle(title, body);
		}
	}
	
	public void modifyArticle(Article article, String title, String body) {
		article.setTitle(title);
		article.setBody(body);
	}
	
	public void deleteArticle(Article article) {
		articles.remove(article);
	}
	
	public int addArticle(String title ,String body) {
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

	public List<Article> getArticles() {
		return articles;
	}
	
}
