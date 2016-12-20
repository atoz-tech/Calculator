package mylib.calculator.spec;

public interface Calculator<T> {
	
	public T add (T operand1, T operand2);
	
	public T sub (T operand1, T operand2);
	
	public T mul (T operand1, T operand2);
	
	public T div (T operand1, T operand2);
	
	public T eval(String expression);

}
