package com.kkwo.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkwo.demo.repository.MemberRepository;

@Service
public class MemberService {
	@Autowired
	private MemberRepository memberRepository;
	
	public int doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {
		memberRepository.doJoin(loginId, loginPw, name, nickname, cellphoneNum, email);
		int id = memberRepository.getLastInsertId();
		return id;
	}
	
}
