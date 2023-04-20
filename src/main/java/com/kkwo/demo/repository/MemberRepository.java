package com.kkwo.demo.repository;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberRepository {

	void doJoin();
	
	int getLastInsertId();

}