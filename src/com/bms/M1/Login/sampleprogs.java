package com.bms.M1.Login;

import java.util.Arrays;

public class sampleprogs {

	public static void main(String[] args) {
	/*	
		I am mentioning those small problems and programs here:

			1. Print below pattern: This program is one of the best to understand the for… loop
			1
			12
			123
			1234
			12345

			public static void main( String[] args ) throws FileNotFoundException {
			for( int i=1; i<=5; i++ ){
			for( int j=1; j<=i; j++ ){
			System.out.print ( j );
			}
			System.out.println (); //to print new line for each iteration of outer loop
			}
			}

			2. Print below pattern: This program is one of the best to understand the for… loop
			*
			***
			*****
			*******
			*********

			public static void main( String[] args ){
			int p = 0;
			for( int i=1; i<=5; i++ ){
			for( int k=1; k<=5-i; k++ ){
			System.out.print (" ");
			}
			for( int j=1; j<=i+p; j++ ){
			System.out.print ("*");
			}
			System.out.println ();
			p=p+1;
			}
			}

			3. Program to read from file line by line:
			bufferedReader class provides readLine method to be used to read the file line by line.

			public static void readFile() throws FileNotFoundException {
			FileReader fr = new FileReader("C:\\Users\\...\\Desktop\\unused.txt");
			BufferedReader br = new BufferedReader(fr);
			StringBuffer str = new StringBuffer();
			try {
			while (br.readLine()!= null){
			str.append(br.readLine());
			}
			} catch (IOException e) {
			e.printStackTrace();
			}
			System.out.println(str);
			}

			4. Reverse a string without using reverse function

			public static void reverse() {
			String str = "I use selenium webdriver. selenium is a tool for web applications.";
			int i = str.length();
			StringBuffer strb = new StringBuffer();
			for( int j=i-1; j>=0; j--){
			strb = strb.append(str.charAt(j));
			}
			System.out.println(strb);
			}

			5. Replace substring with another string in a string

			public static void replace() {
			String str = "I use selenium webdriver. selenium is a tool for web applications automation.";
			String toBeReplaced = "selenium";
			String toReplacedWith = "Firefox";
			String[] astr = str.split(toBeReplaced);
			StringBuffer strb = new StringBuffer();
			for ( int i = 0; i <= astr.length - 1; i++ ) {
			strb = strb.append( astr[i] );
			if (i != astr.length - 1) {
			strb = strb.append(toReplacedWith);
			}
			}
			System.out.println(strb);

			}	
		*/
		
		
		
		/*String str  = "My name is Khan";
		String finalstr="";
		char arr[] = str.toCharArray();
		int length = arr.length;
		System.out.println(arr.length);
		for (int i =length-1;i>=0;i--){
			
			
			System.out.print(finalstr+=arr[i]);
		}
		
		//finalstr = Arrays.toString(arr);
		
		
		System.out.println("Final"+finalstr);*/
		
		
		String str  = "My name is Khan";
			    char ch[]=str.toCharArray();  
			    String rev="";  
			    for(int i=ch.length-1;i>=0;i--){  
			        rev+=ch[i];  
			        
			    } 
			    
			    System.out.println(rev);
			  
			
		
		
		
		
		
		
		
		
		
		
		
		
		
		// TODO Auto-generated method stub

	}

}
