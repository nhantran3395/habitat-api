package com.epam.hackathongood.util;

import java.util.Calendar;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTUtils {
    
	private static final String salt = "sssdft67!";
	/*
	 * generate token header.playload.signature
	 * param:map should contain user info,like userid,username and so on
	 * param:can't put password into map
	 */
	public static String getToken(Map<String,String> map) {
		Calendar instance = Calendar.getInstance();
		instance.add(Calendar.HOUR_OF_DAY, 24*7);
		JWTCreator.Builder builder = JWT.create();
		builder.withExpiresAt(instance.getTime());
		//payload
		map.forEach((k,v)->{
			builder.withClaim(k, v);
		});
		
		//signature
		String token = builder.sign(Algorithm.HMAC256(salt));
		System.out.println("token:" + token);
		return token;
	}
	
	public static void verify(String token) {
		JWT.require(Algorithm.HMAC256(salt)).build().verify(token);
	}
	
	public static DecodedJWT getTokenInfo(String token) {
		DecodedJWT verify = JWT.require(Algorithm.HMAC256(salt)).build().verify(token);
		return verify;
	}
}
