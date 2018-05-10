package com.bms.M1.Login;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import com.gargoylesoftware.htmlunit.javascript.host.Iterator;

public class HelloWorld {

	public static void main(String[] args) throws Throwable{

		
		// Remove duplicate from array list
		
		/*          ArrayList<Object> al = new ArrayList<Object>();

          al.add("java");
          al.add('a');
          al.add('b');
          al.add('a');
          al.add("java");
          al.add(10.3);
          al.add('c');
          al.add(14);
          al.add("java");
          al.add(12);

          System.out.println("Before Remove Duplicate elements:"+al);

        Map <Object , Integer> hm = new HashMap<Object , Integer> ();
        for(int i =0 ; i< al.size() ; i++)
        {
        	if(hm.containsKey(al.get(i)))
        	{
        		int a= hm.get(al.get(i));
        		a++;
        		hm.put(al.get(i), a);
        	}
        	else
        	{
        		hm.put(al.get(i), 1);
        	}

          }

         for( Object a : hm.keySet())
         {
          System.out.println("After Removing duplicate elements:"+ a);
         }

         System.out.println("After Removing duplicate elements:"+ hm);





         String original = "edcbaeb";

         Set<Character> ts = new TreeSet<Character> ();
         for(int i =0 ;i< original.length() ; i++)
         {
        	 ts.add(original.charAt(i));
         }
         System.out.println("original :"+ original);

         System.out.println("original asc"+ ts);*/


		/*  // For each Loop 
	  int a[] ={3,2,1,4,2,1};
		  String[] fruits = new String[] { "Orange", "Apple", "Pear", "Strawberry" };

	  System.out.println("Before sorting");

	  for (String fruit : fruits) {
		    // fruit is an element of the `fruits` array.
		  System.out.println(fruit);
		}

	  for (int num : a) {
		    // fruit is an element of the `fruits` array.
		  System.out.println(num);
		}*/


		/* // Remove duplicates and sort string
		   String original = "edcbaebcbe";

	         Set<Character> ts = new TreeSet<Character> ();
	         for(int i =0 ;i< original.length() ; i++)
	         {
	        	 ts.add(original.charAt(i));
	         }
	         System.out.println("original :"+ original);

	         System.out.println("original asc"+ ts);*/



		/*	// sort and remove duplicate without using collections

		int a[] ={3,2,1,4,2,1};

		System.out.println("Before sorting");
		for(int num:a)
			System.out.print(num);

		for(int i=0;i<a.length;i++){

			for(int j=i;j<a.length;j++){

				if (a[i]>a[j]){

					int temp = a[i];
					a[i]=a[j];
					a[j]=temp;

				}
			}

		}

		System.out.println("After sorting");

		for(int sort :a)

			System.out.print(sort);

		System.out.println("\nafter removing duplicate");

		int b=0;
		a[b]=a[0];

		for(int i=0;i<a.length;i++){

			if(a[b]!=a[i]){


				b++;
				a[b]=a[i];
			}
		}

		for (int i=0;i<=b;i++ )
		{
			System.out.print(a[i]+"\t");
		}*/


		/* String str= "1234abcdAB";  // replace charter numeric from string
  System.out.println(str.replaceAll("[a-z,A-Z]",""));*/

		/*	int[] numbers = { 1, 5, 23,5,23, 2, 1, 6, 3, 1, 8, 12, 3 };  // print duplicate values in array
		Arrays.sort(numbers);

		for(int i = 0; i < numbers.length-1; i++) {
		if(numbers[i] == numbers[i+1]) {
		System.out.print(" " + numbers[i]);
		}

		}
		 */


		/*// Program to print pyramid using numbers

		  int rows = 5, k = 0, count = 0, count1 = 0;

	        for(int i = 1; i <= rows; ++i) {
	            for(int space = 1; space <= rows - i; ++space) {
	                System.out.print("  ");
	                ++count;
	            }

	            while(k != 2 * i - 1) {
	                if (count <= rows - 1) {
	                    System.out.print((i + k) + " ");
	                    ++count;
	                }
	                else {
	                    ++count1;
	                    System.out.print((i + k - 2 * count1) + " ");
	                }

	                ++k;
	            }
	            count1 = count = k = 0;

	            System.out.println();
	        }
		 */



		/*	
		// Program to print pyramid using *
		   int rows = 5, k = 0;

	        for(int i = 1; i <= rows; ++i, k = 0) {
	            for(int space = 1; space <= rows - i; ++space) {
	                System.out.print("  ");
	            }

	            while(k != 2 * i - 1) {
	                System.out.print("* ");
	                ++k;
	            }

	            System.out.println();

	        }
		 */



		//	Count the number of occurrences of a character in a String?


		/*
	String str = "abcdcabcdacbdadbcaaaa";
	int counter = 0;

	for(int i=0;i<str.length();i++){

		if(str.charAt(i)=='a'){

			counter++;


		}


	}

	System.out.println(counter);*/


		/*	//java program to check Armstrong Number.

		int n= 153;
		int c=0,a,temp;

		temp=n;

		while(n>0){


			a=n%10;
			n=n/10;
			c=c+(a*a*a);	
		}

		if(temp==c){

			System.out.println("YES armstrong");

		}else{

			System.out.println("NoT armstrong");


		}*/



		/*	 // fabbonacci series in java

		int num1=0;
		int num2=1;


		for(int i=0;i<1000;i++){

			int sum = num1+num2;

			if(sum<=1000){

				num1=num2;
				num2=sum;
				System.out.println(sum+" ");	
			}



		}
		 */	



		/*	//Find the Factorial of Given Number in java
	int num= 4; int fact=1;


	for(int i=1;i<=num;i++){


		fact=fact*i;

	}

	System.out.println(fact);*/
		
		  
		
		
		
	/*	//How to Add elements to hash map and Display?
		
		  HashMap<Object, Object> map=new HashMap<Object, Object>();
		  map.put(1,"ajay");
		  map.put("d", "kumar");
		  map.put("3",5);
		  
		 for(Map.Entry<Object,Object>entry :map.entrySet()){
			 
			 System.out.println(entry.getKey()+"  "+entry.getValue());
			 
			 
		 }*/
           
          
		
	/*	
		//How to find largest element in an array with index and value using array?
          int a[] = {12,13,14,25,11,47};
          
          int max = a[0];
          int index = 0;
          
          for(int i=0;i<a.length;i++){
        	  
        	  if(max<a[i])
        		  
        	  {
        		  
        		  max=a[i];
        		 index=i;
        		  
        	  }
        	  
        	 
        	  
          }
          System.out.println(max);
          System.out.println(index);*/

         
		
		
		
		
		
		
		/*//Java Program for Binary Search
		   int c, first, last, middle, n, search, array[];

		    Scanner in = new Scanner(System.in);
		    System.out.println("Enter number of elements");
		    n = in.nextInt();
		    array = new int[n];

		    System.out.println("Enter " + n + " integers");


		    for (c = 0; c < n; c++)
		      array[c] = in.nextInt();

		    System.out.println("Enter value to find");
		    search = in.nextInt();

		    first  = 0;
		    last   = n - 1;
		    middle = (first + last)/2;

		    while( first <= last )
		    {
		      if ( array[middle] < search )
		        first = middle + 1;   
		      else if ( array[middle] == search )
		      {
		        System.out.println(search + " found at location " + (middle + 1) + ".");
		        break;
		      }
		      else
		         last = middle - 1;

		      middle = (first + last)/2;
		   }
		   if ( first > last )
		      System.out.println(search + " is not present in the list.\n");
		*/


       
		
	/*	//Prime no. in java
		
		int num=50;
        int count=0;

        for(int i=2;i<=num;i++){

                    count=0;

                    for(int j=2;j<=i/2;j++){

                                if(i%j==0){
                                            count++;
                                            break;
                                }

                    }

                    if(count==0){

                                System.out.print(i+" ");

                    }

        }
*/
		
		/*
		//Java Program to Swap two numbers without using third variable in java
		  int number1=20;
          int number2=30;

          System.out.println("Before Swapping");
          System.out.println("Value of number1 is :" + number1);
          System.out.println("Value of number2 is :" +number2);

          number1=number1+number2;
          number2=number1-number2;
          number1=number1-number2;

          System.out.println("After Swapping");
          System.out.println("Value of number1 is :" + number1);
          System.out.println("Value of number2 is :" +number2);*/


	/*//	Java Program to find sum of digits with and without using recursion.

		 int number;
         Scanner in = new Scanner(System.in);

         System.out.println("Please Enter a number");

         number=in.nextInt();

         int sum=0 ;

         while(number!=0){

                     sum=sum+(number%10);
                     number=number/10;
         }

         System.out.println("Sum of Digits ="+sum);*/


          /*// Java program to remove duplicate elements from an arraylist without using collections (without using set)
		   ArrayList<Object> al = new ArrayList<Object>();

           al.add("java");
           al.add('a');
           al.add('b');
           al.add('a');
           al.add("java");
           al.add(10.3);
           al.add('c');
           al.add(14);
           al.add("java");
           al.add(12);

           System.out.println("Before Remove Duplicate elements:"+al);

           for(int i=0;i<al.size();i++){

                       for(int j=i+1;j<al.size();j++){
                                   if(al.get(i).equals(al.get(j))){
                                               al.remove(j);
                                               j--;
                                   }
                       }

           }

           System.out.println("After Removing duplicate elements:"+al);*/



         // Palindrome program in java using for loop

       /* String str="MADAM";
        String revstring="";

        for(int i=str.length()-1;i>=0;--i){
                    revstring +=str.charAt(i);
        }

        System.out.println(revstring);

        if(revstring.equalsIgnoreCase(str)){
                    System.out.println("The string is Palindrome");
        }
        else{
                    System.out.println("Not Palindrome");
        }*/


      /*  // Sort the string using String Methods?
		

        String original = "edcba";

        char[] chars = original.toCharArray();

        Arrays.sort(chars);

        String sorted = new String(chars);
        System.out.println(sorted);
*/
		
		
		
		
		
/*		
		// Sorting string without using string Methods?

		String original = "edcba";
		int j=0;
		char temp=0;

		  char[] chars = original.toCharArray();

		  for (int i = 0; i <chars.length; i++) {

		      for ( j = 0; j < chars.length; j++) {

		       if(chars[j]>chars[i]){
		            temp=chars[i];
		           chars[i]=chars[j];
		           chars[j]=temp;
		       }

		   } 

		}

		for(int k=0;k<chars.length;k++){
		System.out.println(chars[k]);
		}*/



		
		
		/*write a program to create singleton class?
				package javaprgms;

		public class Singleton {

		            static Singleton obj;
		            private  Singleton(){
		            }

		            public static Singleton getInstance(){
		                        if(obj!=null){
		                                    return  obj;
		                        }
		                        else{
		                                    obj=new Singleton();
		                                    return obj;
		                        }
		            }

		            public static void main(String[] args) {

		                        Singleton obj=Singleton.getInstance();
		                        Singleton obj1=Singleton.getInstance();

		                        if(obj==obj1){
		                                    System.out.println("indhu");
		                        }
		                        else{
		                                    System.out.println("Sindhu");
		                        }
		                        System.out.println(obj==obj1);

		            }

		}

*/
		
		/*int m=0;
	 int n =1;
	 
		for(int i =1;i<100;i++){
		int sum = m+n;
		if(sum<100){
			m = n;
			n = sum;
			System.out.println(sum+ "" );	
				
		}	
		}*/
		
		
		/*int num1=0;
		int num2=1;


		for(int i=0;i<1000;i++){

			int sum = num1+num2;

			if(sum<=1000){

				num1=num2;
				num2=sum;
				System.out.println(sum+" ");	
			}
		
		*/
		
		
		
		
		
		

		/*class PrimeNumbers
		{
		   public static void main (String[] args)
		   {		
		       int i =0;
		       int num =0;
		       //Empty String
		       String  primeNumbers = "";

		       for (i = 1; i <= 100; i++)         
		       { 		  	  
		          int counter=0; 	  
		          for(num =i; num>=1; num--)
			  {
		             if(i%num==0)
			     {
		 		counter = counter + 1;
			     }
			  }
			  if (counter ==2)
			  {
			     //Appended the Prime number to the String
			     primeNumbers = primeNumbers + i + " ";
			  }	
		       }	
		       System.out.println("Prime numbers from 1 to 100 are :");
		       System.out.println(primeNumbers);
		   }
		}
*/

/*
                     // prime no.
		
				for(int i =2 ;i< 100 ; i++)
				{
					int count=0;
					for(int j=1; j<=i ; j++)
					{
						if(i%j==0)
						{
							count++;
						}
					}
					if(count==2)
					{
						System.out.println("num is :" +i);
					}
				}
			 
*/

// 
/*int num =5;
int fact = 1;


for(int i=1;i<num;i++){
	
	
	fact =fact*i;
}

System.out.println(fact);*/
		
		
	/*int arr[] = new int[]{1,2,3,4,5,5,4,3,2,1};
	
	Set<Integer> rd = new TreeSet<Integer>();
	
	for(int i=0;i<arr.length;i++){
		
		rd.add(arr[i]);
		
	}
		
		
  for(Integer finalarr : rd){
	  
	  System.out.println(finalarr);
  }
*/


		ArrayList<String> al=new ArrayList<String>();
      
        al.add("name");
        al.add("      ");
        al.add("a");
      //  System.out.println(al);

        
        for(int i = 0; i < al.size(); i++){
           
        	
        	if(al.get(i).toString().trim().isEmpty()){
        		
        		//System.out.println("Found Blank");
        		al.remove(i);
        	}
        }
        
      /*  try{  
        	   int data=25/0;  
        	   System.out.println(data);  
        	  }  
        	 // catch(ArithmeticException e){System.out.println(e);}  
        	  finally{System.out.println("finally block is always executed");}  
        	  System.out.println("rest of the code...");  */
        
        	  
        	 //  static void validate(int age){  
        int age = 0;
        		     if(age<18)  
        		    	 throw new Exception  ("not valid");  
        		     else  
        		      System.out.println("welcome to vote");  
        		//   }  
      





















	}
	
	
	
	
}










