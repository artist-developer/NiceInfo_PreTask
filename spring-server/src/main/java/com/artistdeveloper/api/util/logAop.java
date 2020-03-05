package com.artistdeveloper.api.util;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Aspect
public class logAop {
	
	private static final Logger logger = LoggerFactory.getLogger(logAop.class);
	

	@Around("execution(* com.artistdeveloper.api.controller..*.*(..))")
	public Object setMapParamter(ProceedingJoinPoint joinPoint) throws Throwable { //
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
		logger.warn("################################################################################################");
		logger.warn("protocol    :  " + request.getProtocol());
		logger.warn("URL         :  " + request.getRequestURL());
		logger.warn("method      :  " + request.getMethod());
		logger.warn("referer     :  " + request.getHeader("referer"));
		Enumeration params = request.getParameterNames();
		int index = 1;
		while (params.hasMoreElements()) {
			String name = (String) params.nextElement();
			logger.warn("param [" + (index++) + "]   :  " + name + " - " + request.getParameter(name));
		}
		logger.warn("################################################################################################");
		return joinPoint.proceed();
	}
}
