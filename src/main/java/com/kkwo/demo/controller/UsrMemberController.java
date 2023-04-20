package com.kkwo.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kkwo.demo.service.MemberService;
import com.kkwo.demo.util.Ut;
import com.kkwo.demo.vo.Member;
import com.kkwo.demo.vo.ResultData;

@Controller
public class UsrMemberController {
	@Autowired
	private MemberService memberService;

	@RequestMapping("/usr/member/join")
	@ResponseBody
	public ResultData<Member> doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum,
			String email) {
		
		if(Ut.empty(loginId)) {
			return ResultData.from("F-1", "아이디를 입력해주세요");
		}
		if(Ut.empty(loginPw)) {
			return ResultData.from("F-2", "비밀번호를 입력해주세요");
		}
		if(Ut.empty(name)) {
			return ResultData.from("F-3", "이름을 입력해주세요");
		}
		if(Ut.empty(nickname)) {
			return ResultData.from("F-4", "닉네임을 입력해주세요");
		}
		if(Ut.empty(cellphoneNum)) {
			return ResultData.from("F-5", "전화번호를 입력해주세요");		
		}
		if(Ut.empty(email)) {
			return ResultData.from("F-6", "이메일을 입력해주세요");
		}
		
		ResultData joinRd = memberService.doJoin(loginId, loginPw, name, nickname, cellphoneNum, email);
		
		if(joinRd.isFail()) {
			return joinRd; 
		}
		int id = (int) joinRd.getData1();
		
		Member member = memberService.getMemberById(id);
		return ResultData.newData(joinRd, member);
	}

}