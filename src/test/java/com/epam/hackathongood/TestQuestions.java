package com.epam.hackathongood;

import java.util.HashMap;
import java.util.Map;

public class TestQuestions {
    public static void main(String[] args) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("1", "2");
		map.put("1", "3");
		System.out.println(map.get("1"));
	}
}
