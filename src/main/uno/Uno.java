package main.uno;

import main.stack.Stack;
import java.lang.Integer;
import java.lang.Math;
public class Uno {
	Stack stack = new Stack();

	public Uno(){}

	public void handleCommand(String cmd){
		if(isInt(cmd)){
			stack.add(Double.parseDouble(cmd));
		} else {
			switch(cmd){
			case "*":
				if(stack.size()<2 ){break;}
				stack.add(stack.pop()*stack.pop());
				break;
			case "-":
				if(stack.size()<2 ){break;}
				Double tult = stack.pop();
				Double tant = stack.pop();
				stack.add(tult-tant);
				break;
			case "+":
				if(stack.size()<2 ){break;}
				stack.add(stack.pop()+stack.pop());
				break;
			case "/":
				if(stack.size()<2 ){break;}
				Double ult = stack.pop();
				Double ant = stack.pop();
				stack.add(ult/ant);
				break;
			case "pop":
				stack.pop();
				break;
			case "sqrt":
				stack.add(Math.sqrt(stack.pop()));
				break;
			case "chs":
				stack.add(stack.pop()*-1);
				break;
			case "dup":
				stack.add(stack.peek());
				break;
			case "swap":
				if(stack.size()<2 ){System.out.println("swap loucura"); break;}
				Double pult = stack.pop();
				Double pant = stack.pop();
				stack.add(pult);
				stack.add(pant);
				break;
			default: 
				System.out.println(cmd);
				System.out.println("operador");
			}
		}
	}
	public Double top(){
		Double element =  stack.peek();
		return element;
	}
	public int size(){
		return stack.size();
	}
	static boolean isInt(String s)
	{
		try
			{ int i = Integer.parseInt(s); return true; }

		catch(NumberFormatException er)
			{ return false; }
	}
}
