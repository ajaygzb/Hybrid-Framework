package com.bms.M1.Login;

public class TestString {

	public static void main(String[] args) {
		
		/*String s1= "Welcome to India";
		String s2= "Thanks for Visiting INDIA";
		String s3 = s1.substring(s1.length()-5,s1.length());
		String s4= s2.substring(s2.length()-5,s2.length());
		String s5= s2.substring(0,3);
		System.out.println(s3);
		System.out.println(s4);
		System.out.println(s5);*/
		
		String str = "helloslkhellodjladfjhellohello";
		String findStr = "hello";
		//String findStr2 = "hello";
		String s1="000000000001251225452210000000000";  
		int lastIndex = 0;
		int count = 0;
		
		System.out.println("Savita's'*****"+s1.replaceAll("0",""));
		System.out.println("Savita's-2'*****"+s1.replaceAll("\\W",""));
		System.out.println("Savita's-3'*****"+s1.replaceAll("[0-9]","*"));
		
		
		System.out.println(str.indexOf(findStr, lastIndex));

		while (lastIndex < str.length()) {

		    lastIndex = str.indexOf(findStr, lastIndex);
		    count++;
	        lastIndex += findStr.length();

		   /* if (lastIndex < str.length()) {
		        count++;
		        lastIndex += findStr.length();
		        
		        
		    }*/
		}
		System.out.println(count);
		
	//	System.out.println((findStr+findStr2).length());
		
		String[] words=s1.split("\\s");
		
		for(String c:words){
			
			System.out.println(c);
		}
		
		System.out.println(s1.replaceAll("[a-zA-Z\\s\\W]",""));
		
		
		
		

	}

}
