package com.kkwo.demo.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MemberRepository {

	@Insert("""
			INSERT INTO `member`
			SET regDate = NOW(),
			updateDate = NOW(), 
			loginId = #{loginId},
			loginPw = #{loginPw},
			`name` = #{name},
			nickname = #{nickname},
			cellphoneNum = #{cellphoneNum},
			email = #{email}
			""")
	void doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email);
	
	@Select("SELECT LAST_INSERT_ID()")
	int getLastInsertId();

}