import java.util.*;
public class interprete {
	private Map<String, double[][]> variables=new HashMap<String, double[][]>();
	public interprete(){
		double[][] pi={{Math.PI}};
		double[][] e={{Math.E}};
		variables.put("pi",pi);
		variables.put("e",e);
		
		
	}
	
	
	public double[][] polaca(String a) {
		String[] partes=a.split("[ ]");
		Stack<double[][]> pila=new Stack<double[][]>();
		double [][] aux=new double[1][1];
		double [][] num=new double[1][1];
		double [][] num1=new double[1][1];
		int k=0;
		try{
		for(String exp: partes){
			
			if(exp.equals("+"))
				pila.push(Matriz.suma(pila.pop(),pila.pop()));
			else if(exp.equals("-"))
				pila.push(Matriz.suma(Matriz.escalar(pila.pop(),-1),pila.pop()));
			else if(exp.equals("*")){
				num=pila.pop();
				num1=pila.pop();
				if((num.length==1 && num[0].length==1))
					pila.push(Matriz.escalar(num1, num[0][0]));

				else if((num1.length==1 && num1[0].length==1))
					 pila.push(Matriz.escalar(num, num1[0][0]));
				else
					pila.push(Matriz.producto(num1, num));
				
				
			}
				
			else if(exp.equals("^")){
				k=(int)(pila.pop())[0][0];
				pila.push(Matriz.potencia(pila.pop(),k));
			}
				
			else if(exp.equals("inv"))
				pila.push(Matriz.inversa(pila.pop()));
			else if(exp.equals("tra"))
				pila.push(Matriz.transpuesta(pila.pop()));
			else if(exp.equals("det")){
			aux[0][0]=Matriz.determinante(pila.pop());
			
				
			pila.push(aux);
			}
			else
				if(exp.matches("[-]?\\d+")){
				
					aux[0][0]=Integer.parseInt(exp);
					pila.push(Matriz.copia(aux));
					aux[0][0]=0;
				}
				else				
				if(variables.get(exp)!=null)
					pila.push(Matriz.copia(variables.get(exp)));
			
		}
		}
		catch (Ematriz e) {
			System.out.println(e.getmensaje());
			return null;
		}
		catch (EmptyStackException e) {
			System.out.println("expresion incosistente");
			return null;
		}
	  if(pila.empty())
		  return null;

		return pila.pop();
		
	}
	
	public String infijoPosfijo(String b)throws Ematriz{
		String posfiga="";
		Stack<String> pilaParentesis=new Stack<String>();
		Stack<String> posfija=new Stack<String>();
		String temporal="";
		
		if(!evaluadorEXP.completo(b))
			throw new Ematriz("faltan parentesis");
		
		
		for(String exp:espaciar(b).split("[ ]+"))
			if(exp.equals("+")) 
				
				pilaParentesis.push("+");
		
			else if(exp.equals("-"))
				
				pilaParentesis.push("-");
		
			else if(exp.equals("*"))
				
				pilaParentesis.push("*");
		
			else if(exp.equals("^"))
				
				pilaParentesis.push("^");
		
			else if(exp.equals("det"))
				
				pilaParentesis.push("det");
		
			else if(exp.equals("inv"))
				
				pilaParentesis.push("inv");
		
			else if(exp.equals("tra"))
				
				pilaParentesis.push("tra");
		
			else if(exp.equals("("))
				
				pilaParentesis.push("(");
		
			else if(exp.equals(")")){
				while (!(temporal=pilaParentesis.pop()).equals("("))
					posfija.push(temporal);
				if(!pilaParentesis.empty())
					if(pilaParentesis.peek()=="inv" || pilaParentesis.peek()=="det" || pilaParentesis.peek()=="tra")
						posfija.push(pilaParentesis.pop());
				
			}
			else
				posfija.push(exp);
				
		while(!pilaParentesis.empty())
			posfija.push(pilaParentesis.pop());
		
		for(int i=0;i<posfija.size();i++)
			posfiga+=posfija.get(i)+" ";
	
			return posfiga;
		}

	public double[][] calcular(String a){
		double [][] matriz=null;
		try {
			matriz= polaca(infijoPosfijo(a));
		} catch (Ematriz e) {
			System.out.println(e.getmensaje());
		}
		return matriz;
	}
		
	public String espaciar(String expresion){
		String espaciado="";
		for(int i=0;i<expresion.length();i++){
			if(expresion.substring(i, i+1).equals("+"))
				espaciado+=" + ";
		  else if(expresion.substring(i, i+1).equals("-"))
				espaciado+=" - ";
			else if(expresion.substring(i, i+1).equals("*"))
				espaciado+=" * ";
			else if(expresion.substring(i, i+1).equals("^"))
				espaciado+=" ^ ";
			else if(expresion.substring(i, i+1).equals(")"))
					espaciado+=" ) ";
			else if(expresion.substring(i, i+1).equals("("))
				espaciado+=" ( "; 
			else if(expresion.length()-i>4){
				
					if(expresion.substring(i, i+4).equals("inv(")){
							espaciado+=" inv ( ";
							i+=3;				
					}
					
					else if(expresion.substring(i, i+4).equals("det(")){
						espaciado+=" det ( ";
						i+=3;				
					}
					else if(expresion.substring(i, i+4).equals("tra(")){
						espaciado+=" tra ( ";
						i+=3;				
					}
					else
						espaciado+=expresion.substring(i, i+1);
			}
			else
				espaciado+=expresion.substring(i, i+1);
		}
		return espaciado;
	}
	
	public void Asignar(String entrada){
		
		if(!evaluadorEXP.completo(entrada)){
			System.out.println("faltan parentesis");
			return;
		}
		String entradaSinespacios="";
		for (int x=0; x < entrada.length(); x++) {
			  if (entrada.charAt(x) != ' ')
			    entradaSinespacios+= entrada.charAt(x);
			}
		
		String[] exp=entrada.split("=");
		if(variables.get(exp[0])!=null){
			System.out.println("ya existe una variable con este nombre");
			return;
		}
			
		if(!evaluadorEXP.nombreValido(exp[0])){
			System.out.println("nombre invalido los nombre deben \n empezar con una letra y solo pueden contener numeros y letras");
			return;
		}
	if(exp[1].indexOf("{")==-1){
			variables.put(exp[0], calcular(exp[1]));
			return;
		}

		int []tam=tama(exp[1]);
		double [][]matriz=new double[tam[1]][tam[0]];
		String [] particion=exp[1].split("[,;{} ]+");
		if(tam[0]*tam[1]!=(particion.length-1))
			System.out.println("tamaño incositente faltan elementos");
		int c=1;
		for(int i=0;i<matriz.length;i++)
			for(int j=0;j<matriz[0].length;j++){
				matriz[i][j]=Integer.parseInt(particion[c]);
				c++;				
			}	
		    variables.put(exp[0], matriz);
	}
	
	public static int[]  tama(String a){
		int [] u={0,0};		
		for(int i=0;i<a.length();i++)
			if(a.charAt(i)==',')
				u[0]++;
			else if(a.charAt(i)==';')
				u[1]++;		
		
		u[0]+=u[1];
		u[0]++;
		u[1]++;
		u[0]/=u[1];		
		return u;	
	}
}
