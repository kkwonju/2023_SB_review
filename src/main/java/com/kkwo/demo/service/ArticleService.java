package com.kkwo.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkwo.demo.repository.ArticleRepository;
import com.kkwo.demo.util.Ut;
import com.kkwo.demo.vo.Article;
import com.kkwo.demo.vo.ResultData;

@Service
public class ArticleService {
	@Autowired
	private ArticleRepository articleRepository;
	
	public ArticleService(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}
	
	/* 게시글 작성 */
	public ResultData writeArticle(String title, String body) {
		articleRepository.writeArticle(title, body);
		int id = articleRepository.getLastInsertId();
		return ResultData.from("S-1", Ut.f("%d번 글 생성", id), id);
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
	public void modifyArticle(int id, String title, String body) {
		articleRepository.modifyArticle(id, title, body);
	}
	
	/* 게시글 삭제 */
	public void deleteArticle(int id) {
		articleRepository.deleteArticle(id);
	}
}
