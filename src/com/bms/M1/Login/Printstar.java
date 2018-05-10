package com.bms.M1.Login;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Printstar {

	public static void main( String[] args ){
	/*	int p = 0;
		for( int i=1; i<=5; i++ ){
		for( int k=1; k<=5-i; k++ ){
		System.out.print (" ");
		}
		for( int j=1; j<=i+p; j++ ){
		System.out.print ("*");
		}
		System.out.println ();
		p=p+1;
		}*/
		//printstarpyrmid();
	//	removespace();
		Stringrev();
		//listtoset();
		}




public static void printstarpyrmid(){
	
	// Need to print a pyramid for 5 rows with start
	int p=0;
	for(int i=1;i<=10;i++){
		
		for(int j =1;j<=10-i;j++){
			
			System.out.print(" ");
		}
		
		for(int k=1;k<=p+i;k++){
			
			System.out.print("*");
		}
		System.out.println();
		
		p=p+1;
	}

}

public static void removespace(){
	
	 String s = "AJAY KUMAR";        
	/*StringTokenizer st = new StringTokenizer( s, " " );
	
	StringBuffer bf = new StringBuffer();
	while(st.hasMoreTokens())
	{
	    bf.append(st.nextToken()).append(" ");
	}
	*/
	String arr[] =s.split(" ");
	String reverse="";
	for(String s2:arr){
	      char arr2[]=s2.toCharArray();
		for(int i=arr2.length-1;i>=0;i--){
			reverse=reverse+arr2[i];
			//System.out.print(arr2[i]);
			
		}
	
		reverse=reverse+" ";
	}
	
	
	System.out.println(reverse);
	
    

}

// Revrese string with spaces
public static void Stringrev(){
String s="My Name Is Savita";
StringBuilder sb= new StringBuilder(s);
StringBuffer sbu= new StringBuffer(s);
//sb.append(s);
sb.reverse();
sbu.reverse();
System.out.println(sb);

}

public static void Stringrev1(){
String s="My Name Is KHAN";
char arr[]=s.toCharArray();
//System.out.println(sb);

}

public static void listtoset()
{
	System.out.println("List values .....");
	List<String> list = new ArrayList<String>();
    list.add("1");
    list.add("2");
    list.add("3");
    list.add("4");
    list.add("1");
    
    for (String temp : list){
    	System.out.println(temp);
    }
    
    Set<String> set = new HashSet<String>(list);
    
    System.out.println("Set values .....");
    for (String temp : set){
    	System.out.println(temp);
    }

Hashtable<String,Integer> HT = new Hashtable<String,Integer> ();
//HT.put(key, value)
HashMap<String,Integer> HM = new HashMap<String,Integer> ();
//Arrays.asList(arr[]);






}


}