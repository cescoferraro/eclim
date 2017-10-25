package main.uno;

import main.stack.Stack;
import java.lang.Integer;

public class Uno {
	Stack stack = new Stack();
	public Uno(){
	}
	public void handleCommand(String cmd){
		if(isInt(cmd)){
			stack.add(Integer.parseInt(cmd));
		} else {
			switch(cmd){
			case "*":
				Integer iult = (Integer)stack.pop();
				Integer iant = (Integer)stack.pop();
				stack.add(iant*iult);
				break;
			case "-":
				Integer tult = (Integer)stack.pop();
				Integer tant = (Integer)stack.pop();
				stack.add(tult-tant);
				break;
			case "+":
				Integer qult = (Integer)stack.pop();
				Integer qant = (Integer)stack.pop();
				stack.add(qant+qult);
				break;
			case "/":
				Integer ult = (Integer)stack.pop();
				Integer ant = (Integer)stack.pop();
				stack.add(ult/ant);
				break;
			case "dup":
				stack.add(stack.peek());
				break;
			case "swap":
				if(stack.size()==1){
					break;
				}
				Integer pult = (Integer)stack.pop();
				Integer pant = (Integer)stack.pop();
				stack.add(pult);
				stack.add(pant);
				break;
			default: 
				System.out.println("operador");
			}
		}
	}
	public Integer top(){
		Integer element = (Integer) stack.peek();
		return element;
	}

	static boolean isInt(String s)
	{
		try
			{ int i = Integer.parseInt(s); return true; }

		catch(NumberFormatException er)
			{ return false; }
	}
}
