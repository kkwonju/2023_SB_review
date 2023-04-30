package com.kkwo.demo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kkwo.demo.vo.Board;

@Mapper
public interface BoardRepository {
	
	@Select("""
			<script>
			SELECT *
			FROM board
			WHERE id = #{boardId}
			AND delStatus = 0;
			</script>
			""")
	public Board getBoardById(int boardId);
}
