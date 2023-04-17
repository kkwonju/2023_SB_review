package com.kkwo.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kkwo.demo.vo.Article;

@Mapper
public interface ArticleRepository {
	public void writeArticle(String title ,String body);
	
	public Article getArticle(int id);
	
	public List<Article> getArticles();
	
	public void modifyArticle(int id, String title, String body);
	
	public void deleteArticle(int id);
	
	public int getLastInsertId();
}
