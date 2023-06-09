package com.kkwo.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.kkwo.demo.interceptor.BeforeActionInterceptor;
import com.kkwo.demo.interceptor.NeedLoginInterceptor;

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

	// BeforeActionInterceptor 불러오기
	@Autowired
	BeforeActionInterceptor beforeActionInterceptor;

	// NeedLoginInterceptor 불러오기
	@Autowired
	NeedLoginInterceptor needLoginInterceptor;

	// 인터셉터 적용
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(beforeActionInterceptor).addPathPatterns("/**").excludePathPatterns("/resource/**")
				.excludePathPatterns("/error");

		registry.addInterceptor(needLoginInterceptor).addPathPatterns("/usr/article/modify")
				.addPathPatterns("/usr/doModify").addPathPatterns("/usr/article/delete")
				.addPathPatterns("/usr/article/write").addPathPatterns("/usr/article/doWrite")
				.addPathPatterns("/usr/member/logout").excludePathPatterns("/resource/**")
				.excludePathPatterns("/error");
	}
}
