package com.weborder;

import java.util.Random;

public class RandomHelp {

	public static void main(String[] args) {
		Random r=new Random();
		int count=0;
		int number=r.nextInt(100)+1;
		while(number!=100) {
			number=r.nextInt(100)+1;
			count++;
			System.out.println("*****");
	}
		System.out.println(count);
		System.out.println(number);
}
	
	
	
}