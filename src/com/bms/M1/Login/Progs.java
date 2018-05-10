package com.bms.M1.Login;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Progs {

	public static void main(String[] args) {
		
		
		 Map<Integer,String> map=new LinkedHashMap<Integer,String>();
	        map.put(1,"one");
	        map.put(2,"two");
	        map.put(3,"three");
	        map.put(4,"four");
	        map.put(5,"five");
	        map.put(15,"five");
	        map.put(25,"five");
	        map.put(35,"five");
	        map.put(22,"two");
	        map.put(32,"two");
	        map.put(42,"eight");
	        
	        Set<String>S=new TreeSet<String>(map.values());
	        System.out.println(S);
	        Iterator<String> it = S.iterator();
	        Map<Integer,String> mapunique=new HashMap<Integer,String>();
	        
	        while(it.hasNext()){
	        	
	        	String value = it.next();
	        	
	        	for(Map.Entry<Integer,String> e:map.entrySet()){
	        		
	        		if(value.equals(e.getValue())&& !mapunique.containsValue(value)){
	        			
	        			mapunique.put(e.getKey(), value);
	        		}
	        		
	        		
	        		
	        	}
	        	
	        
	        
	        
	        }
	        System.out.println(mapunique);
	        
	        
	        
	        
	        
	        

	
	
	
	}

}
