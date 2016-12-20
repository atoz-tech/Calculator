package mylib.calculator.impl;

import java.util.Deque;
import java.util.LinkedList;

import mylib.calculator.exception.IllegalOperatorException;
import mylib.calculator.spec.Calculator;

public class IntCalculator implements Calculator<Integer>{

	@Override
	public Integer add(Integer operand1, Integer operand2) {	
		return operand1 + operand2;
	}

	@Override
	public Integer sub(Integer operand1, Integer operand2) {
		return operand1 - operand2;
	}

	@Override
	public Integer mul(Integer operand1, Integer operand2) {
		return operand1 * operand2;
	}

	@Override
	public Integer div(Integer operand1, Integer operand2) {
		return operand1 / operand2;
	}

	@Override
	public Integer eval(String expression){
		// create stacks for operators and operands
		Deque<Character> op = new LinkedList<>();
		Deque<Integer> val = new LinkedList<>();

		//insert a '0' at front to balance expression
		String input = "0" + expression.replaceAll("-", "+-");
		StringBuilder temp = new StringBuilder();

		//tokenize the expression into numbers an operands
		for (int i = 0; i < input.length(); i++){
			char ch = input.charAt(i);
			if(ch == '-'){
				temp = temp.insert(0,  '-');
			}
			else if(ch!= '+' && ch != '*' && ch != '/'){
				temp.append(ch);
			}
			else{
				if(temp.length() != 0){
					val.push(Integer.parseInt(temp.toString()));
				}
				op.push(ch);
				temp.setLength(0);
			}

		}
		val.push(Integer.parseInt(temp.toString()));

		//calculate the expression
		while(!op.isEmpty()){
			char operator = op.pop();
			int operand2 = val.pop();
			int operand1 = val.pop();

			switch(operator){
				case '+' :
					val.push(add(operand1, operand2));
					break;
	
				case '*' :
					val.push(mul(operand1, operand2));
					break;
	
				case '/' :
					val.push(div(operand1, operand2));
					break;
	
				default:
					throw new IllegalOperatorException("not a valid operator");
				}
		}
		return val.pop();
	}
	
}
