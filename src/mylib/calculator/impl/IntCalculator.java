package mylib.calculator.impl;

import java.util.Deque;
import java.util.LinkedList;

import mylib.calculator.exception.IllegalOperatorException;
import mylib.calculator.spec.Calculator;

public class IntCalculator implements Calculator<Integer> {

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
    public Integer eval(String expression) {

        Deque<Character> operatorStack = new LinkedList<>();
        Deque<Integer> operandStack = new LinkedList<>();

        StringBuilder operand = new StringBuilder();
        String input = "0" + expression.replaceAll("-", "+-");

        for (int i = 0; i < input.length(); i++) {
            char token = input.charAt(i);
            if (token != '+' && token != '*' && token != '/') {
                operand.append(token);
            } else {
                if (operand.length() != 0) {
                    operandStack.push(Integer.parseInt(operand.toString()));
                    operand.setLength(0);
                }
                Character operator = operatorStack.peek();
                while (operator != null && ('*' == operator || '/' == operator)) {
                    int operand2 = operandStack.pop();
                    int operand1 = operandStack.pop();
                    operandStack.push(calculate(operand1, operand2, operatorStack.pop()));
                    operator = operatorStack.peekLast();
                }
                operatorStack.push(token);
            }
        }
        operandStack.push(Integer.parseInt(operand.toString()));
        while (operatorStack.peek() != null) {
            int operand2 = operandStack.pop();
            int operand1 = operandStack.pop();
            char operator = operatorStack.pop();
            operandStack.push(calculate(operand1, operand2, operator));
        }
        return operandStack.pop();
    }

    private Integer calculate(Integer operand1, Integer operand2, Character operator) {
        Integer result;
        switch (operator) {
            case '+':
                result = add(operand1, operand2);
                break;
            case '*':
                result = mul(operand1, operand2);
                break;
            case '/':
                result = div(operand1, operand2);
                break;
            default:
                throw new IllegalOperatorException("not a valid operator");
        }
        return result;
    }
}
