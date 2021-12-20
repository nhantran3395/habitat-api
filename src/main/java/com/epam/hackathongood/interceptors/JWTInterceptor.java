package com.epam.hackathongood.interceptors;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
//import com.auth0.jwt.interfaces.DecodedJWT;
import com.epam.hackathongood.util.JWTUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object Handler) throws Exception{
    	Map<String,Object> map = new HashMap<String,Object>();
    	String token = request.getHeader("X-Token");
    	try {
    		JWTUtils.verify(token);
    		return true;
    	}catch(SignatureVerificationException e) {
    		e.printStackTrace();
    		map.put("msg","invalid signature");
    	}catch(TokenExpiredException e) {
    		e.printStackTrace();
    		map.put("msg", "token expired");
    	}catch(AlgorithmMismatchException e) {
    		e.printStackTrace();
    		map.put("msg", "token algorithm don't match");
    	}catch(Exception e) {
    		map.put("msg", "token invalid");
    	}
    	map.put("state", false);
    	//convert map to json
    	String json = new ObjectMapper().writeValueAsString(map);
    	response.setContentType("application/json;charset=UTF-8");
    	response.getWriter().println(json);
    	
    	return false;
    }
}
