package main;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		int num;
		Scanner scan=new Scanner(System.in);
		System.out.print("inserisci numero: ");
		num=scan.nextInt();
		scan.close();
		System.out.print(String.format("il risultato e' %d ", phi(num)));
	}
	
	
	public static int mcd(int n1,int n2) {
	
		int reminder;
	    while(n2 != 0) 
	    {
	        reminder = n1 % n2;
	        n1 = n2; 
	        n2 = reminder; 
	   }
	    
	   return n1;
	}
	
	private static int log(int number,int base) {
		int exponent=0;
		while(number!=1) {
			number/=base;
			exponent++;
		}
		
		return exponent;
	}
	
	public static int phi(int number) {
		int result=0;
		int a=0,b=0;
		if(number ==1) return 1;
		
		for(a=number-1;a>=1;a--) {
			if(number%a==0) {
				b=number/a;
				if(a==1) {
					int p,k;
					for(p=1;mcd(a+p,b)==1;p++);
					p++;
					k= log(b,p);
					result=(p-1)*(int)Math.pow(p, k-1);
					break;
				}
				else if(a==b) {
					int p=1,k;
					for(p=1;mcd(a+p,b)==1;p++);
					k= log(b,p)*2;
					result=(p-1)*(int)Math.pow(p, k-1);
					break;
				}
				else if(mcd(b,a)==1 & a!=b) {
					result= phi(b) * phi(a);
					break;
				}
			
			}
		}
	
		return result;
	}

}
