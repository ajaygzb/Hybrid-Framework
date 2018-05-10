package com.bms.M1.Login;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

class TestApp{  

  
  public static void main(String args[]){  
	  
	  
	   Map<String, String> myMap = new TreeMap<String, String>();
	    myMap.put("1", "One");
	    myMap.put("2", "Two");
	    myMap.put("3", "One");
	    myMap.put("3", "Three");
	    myMap.put("2", "Two");
	    myMap.put("1", "Three");

	   

	    Set<String> k = myMap.keySet();
	    Iterator i = k.iterator();
	    while(i.hasNext()){
	    Object key = i.next();
	    System.out.println(key);
	  
	    
	
    
	    
    
    
 }  
	    System.out.println(myMap.entrySet());
	    
  }
  
}
 