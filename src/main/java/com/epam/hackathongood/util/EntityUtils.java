package com.epam.hackathongood.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class EntityUtils {
    public static Map<String,Object> entityToMap(Object object){
    	Map<String,Object> map = new HashMap<String,Object>();
    	for(Field field:object.getClass().getDeclaredFields()) {
    		try {
    			boolean flag = field.isAccessible();
    			field.setAccessible(true);
    			Object o = field.get(object);
    			map.put(field.getName(), o);
    			field.setAccessible(flag);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}
    	return map;
    }
}
