package com.kkwo.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkwo.demo.repository.MemberRepository;

@Service
public class MemberService {
	@Autowired
	private MemberRepository memberRepository;

	public int doJoin() {
		memberRepository.doJoin();
		int id = memberRepository.getLastInsertId();
		return id;
	}
	
}
