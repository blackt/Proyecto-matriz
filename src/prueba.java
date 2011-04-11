import java.util.*;
public class prueba {


	public static void main(String[] args) {
		System.out.print
		("                 Bienvenido a CMAT(Calculador MATrices) \n" +
				"este programa permite calcular operaciones basicas con matrices \n" +
				"                 version pre alfa  1X10^-9        \n" +
				"     escriba salir para terminar la ejecucion del programa \n" +
				"");
		
		
		
		interprete c=new interprete();
		Scanner lector=new Scanner(System.in);
		String lectura="";
		int i=2;
		System.out.print("interprete"+1+"> ");
		while(!(lectura=lector.nextLine()).equals("salir")){
			if(!(lectura=="\n")){
				
				if(lectura.indexOf("=")!=-1)
					c.Asignar( lectura);
				else
					Matriz.imprimir(c.calcular(lectura));
				System.out.print("interprete"+i+"> ");
			}
			i++;
		}
		
	
	}

}
