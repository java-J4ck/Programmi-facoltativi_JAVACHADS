package main;

import java.util.Scanner;

import operation.Operation;
import parser.Parser;

public class Main {

	public static void main(String[] args) {
		String expression;
		Operation topOperation;
		Scanner scan=new Scanner(System.in);
		System.out.print("inserisci espressione ->");
		expression=scan.next();
		scan.close();
		//expression=expression.replace(' ', '\0');
		topOperation = Parser.buildTree(expression);
		
		System.out.println(String.format("il risultato e': %.4f", topOperation.getResult()));

	}

}
