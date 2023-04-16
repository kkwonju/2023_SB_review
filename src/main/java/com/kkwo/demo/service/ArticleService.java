package com.kkwo.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkwo.demo.repository.ArticleRepository;
import com.kkwo.demo.vo.Article;

@Service
public class ArticleService {
	@Autowired
	private ArticleRepository articleRepository;
	
	public ArticleService(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
		articleRepository.makeTestData();
	}
	
	/* 게시글 작성 */
	public int doWrite(String title, String body) {
		int id = articleRepository.addArticle(title, body);
		return id;
	}
	
	/* 게시글 */
	public Article getArticle(int id) {
		return articleRepository.getArticle(id);
	}
	
	/* 게시글 목록 */
	public List<Article> getArticles() {
		return articleRepository.getArticles();
	}
	
	/* 게시글 수정 */
	public void modifyArticle(Article article, String title, String body) {
		articleRepository.modifyArticle(article, title, body);
	}
	
	/* 게시글 삭제 */
	public void deleteArticle(Article article) {
		articleRepository.deleteArticle(article);
	}
}
