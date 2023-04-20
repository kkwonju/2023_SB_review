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
	void writeArticle(String title ,String body);
	
	Article getArticle(int id);
	
	List<Article> getArticles();
	
	void modifyArticle(int id, String title, String body);
	
	void deleteArticle(int id);
	
	int getLastInsertId();
}
