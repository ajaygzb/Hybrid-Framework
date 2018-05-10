package com.bms.M1.Login;

  abstract class Bike{
   Bike(){
	   System.out.println("bike is created");
	   
   
   
   }
   abstract void run();
   void changeGear(){System.out.println("gear changed");}
 }
  
  class Honda extends Bike{

	@Override
	void run() {
		System.out.println("Implemented Run");
		
	}
	  
	  
	  
	  
  }
  
  
  
  class Abstract{
	  
	  public static void main(String args[]) {
		  
	   Honda h= new Honda();
	  // Bike b= new Bike();
	   h.run();
	   h.changeGear();

	}
	  
	  
	  
	  
	  
  }