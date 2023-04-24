package com.kkwo.demo.vo;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kkwo.demo.util.Ut;

import lombok.Getter;

public class Rq {
	@Getter
	private boolean isLogined;
	@Getter
	private int loginedMemberId;
	
	private HttpServletRequest req;
	private HttpServletResponse resp;
	private HttpSession session;

	public Rq(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
		this.session = req.getSession();
		
		boolean isLogined = false;
		int loginedMemberId = 0;

		if (session.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
		}
		
		this.isLogined = isLogined;
		this.loginedMemberId = loginedMemberId;
	}

	public void login(Member member) {
		session.setAttribute("loginedMemberId", member.getId());
	}
	
	public void printHistoryBackJs(String msg) throws IOException {
		resp.setContentType("text/html; charset=UTF-8;");
		print(Ut.jsHistoryBack("F-A", msg));
	}
	
	public void printReplaceJs(String msg, String uri) {
		resp.setContentType("text/html; charset=UTF-8;");
		print(Ut.jsReplace("F-A", msg, uri));
	}

	public void print(String str) {
		try {
			resp.getWriter().append(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void logout() {
		session.removeAttribute("loginedMemberId");
	}

	public String jsHistoryBackOnView(String resultCode, String msg) {
		req.setAttribute("msg", msg);
		req.setAttribute("historyBack", true);
		return "usr/common/js";
	}
	
//	public void println(String str) {
//		print(str+"\n");
//	}
}
