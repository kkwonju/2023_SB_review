package com.kkwo.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/*
super
interface는 생성자가 없으므로 여기서 super는 부모의 메서드를 호출하기위해 쓰임
부모의 메서드를 실행함과 동시에 오버라이드하기 위함
*/

@Component
public class BeforeActionInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
		System.out.print("실행됌?");
		return HandlerInterceptor.super.preHandle(req, resp, handler);
	}
}