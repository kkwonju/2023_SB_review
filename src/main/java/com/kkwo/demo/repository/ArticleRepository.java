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
	@Insert("INSERT INTO article SET title = #{title}, body = #{body}")
	public void doWrite(String title ,String body);
	@Select("SELECT * FROM article WHERE id = #{id}")
	public Article getArticle(int id);
	@Select("SELECT * FROM article ORDER BY id DESC")
	public List<Article> getArticles();
	@Update("UPDATE article SET title = #{title}, body = #{body}")
	public void modifyArticle(int id, String title, String body);
	@Delete("DELETE FROM article WHERE id = #{id}")
	public void deleteArticle(int id);
	@Select("SELECT LAST_INSERT_ID()")
	public int getLastInsertId();
}
