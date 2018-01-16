package com.blogbox.components;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.blogbox.logs.Logger;

@Component("requestTimeInterceptor")
public class RequestTimeInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = new Logger(RequestTimeInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		request.setAttribute("startTime", System.currentTimeMillis());
		return super.preHandle(request, response, handler);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		long ms = System.currentTimeMillis() - (long) request.getAttribute("startTime");
		logger.info("Request", "URL: '" + request.getRequestURL().toString() + "' -- Tiempo: '" + ms + "ms'");
		super.afterCompletion(request, response, handler, ex);
	}

	

	
	
}
