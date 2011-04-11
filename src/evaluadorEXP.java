import java.util.*;
public class evaluadorEXP {
	public static final String[] tokens={")","(","tra","+","-","*","^","det","inv"};
	
	public static boolean  completo(String cade){
		Stack<String> par=new Stack<String>();
		for(String  p:cade.split("")){
			if(p.equals("("))
				par.push("(");				
			else if(p.equals(")")){				
				if(par.empty())
					return false;				
				par.pop();
			}			
		}		
		return par.empty();
	}
	
	
	public static boolean  nombreValido(String variable){
		if(variable.substring(0,1).matches("\\d+"))
			return false;
		if(!variable.matches("\\w+"))
			return false;		
		return true;		
	}
	
}
