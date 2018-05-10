package com.bms.M1.Login;

import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;

public class Programs {
	
	 public static void main (String[] args) {  
		 
		 //***************************************************remove duplicate fro array*********
		 
	       /* int arr[] = {10,20,20,30,30,40,50,50};  
	        int length = arr.length;  
	        
	        //printing array elements 
	        System.out.println("before remove duplicates:  ");
	        for (int i=0; i<length; i++)  {
	           System.out.print(arr[i]+" ");  
	        }*/
	    
	 
	 
/*	      // without set
 *        int j=0;
	    
	    
	    for(int i=0;i<length-1;i++){
	    	
	    	if(arr[i]!=arr[i+1]){
	    		
	    	arr[j++]=arr[i];	
	    	}	
	    }
	    arr[j++]=arr[length-1];
	
	
	 
	    System.out.println("after remove duplicates:  ");
	    for(int i=0;i<j;i++){
	    	
	    	System.out.print(arr[i]);	 
		 }*/
		
	 //with set
	        
	     /*   Set<Integer>s=new TreeSet<Integer>();
	        for(int i:arr){
	        	
	        	s.add(i);	
	        }
	        
	        System.out.println("after remove duplicates:  "+s);*/
		 
		 
		 //**************************************Pro-1 Ends***************************
		 
		 
		 //************** Sort String array
		 
		/* String[] inputList = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
					"aug", "Sep", "Oct", "nov", "Dec" };*/
		 /* Arrays.sort(inputList);
		  Arrays.sort(inputList, String.CASE_INSENSITIVE_ORDER);
		  for(String e:inputList)
			  System.out.println(e);*/
		/* String temp;
		 for(int i=0;i<inputList.length;i++){
			 
			 for(int j=i+1;j<inputList.length;j++){
				 if(inputList[i].compareTo(inputList[j])>0){
					 
					 temp=inputList[i];
					 inputList[i]= inputList[j];
					 inputList[j]=temp;}}}
		 for(String e:inputList)
			  System.out.println(e);}*/
		 
	/*	 
		 // Program: Write a program to find top two maximum numbers in a array.
		 
		 int arr[]= {5,34,78,2,45,1,99,23};
		 
		 int maxone=0;
		 int maxtwo=0;
		 
		 for(int n:arr){
			 
			 if(maxone<n){
				 
				 maxtwo=maxone;
				 maxone=n;	 
			 }else if(maxtwo<n){
				 
				 maxtwo=n;
			 } 
		 }
		 
		 System.out.println(maxone);
		 System.out.println(maxtwo);*/
		 

				
		 
	 
	 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}


}




