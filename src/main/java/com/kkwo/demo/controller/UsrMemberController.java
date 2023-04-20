package com.kkwo.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kkwo.demo.service.MemberService;

@Controller
public class UsrMemberController {
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/usr/member/join")
	@ResponseBody
	public String doJoin(String loginId, String loginPw) {
		int id = memberService.doJoin();
		return id + "번 회원 가입됨";
	}

}
