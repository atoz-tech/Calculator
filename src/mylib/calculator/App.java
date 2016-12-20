package mylib.calculator;

import mylib.calculator.impl.IntCalculator;
import mylib.calculator.spec.Calculator;

public class App {
	
	public static void main(String[] args){
		
		Calculator<Integer> cal = new IntCalculator();	
		System.out.println("Result:" + cal.eval("-1-1/2"));
	}

}
