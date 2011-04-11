public class Matriz {
	
	public static double[][] suma(double [][]a,double[][] b)throws Ematriz{
		if(!(a.length==b.length && a[0].length==b[0].length))
				throw new Ematriz("tamaño inconsistente");
		
		
		double[][] c=new double[a.length][a[0].length];
		for(int I=0;I<a.length;I++)
			for(int J=0;J<a[0].length;J++)
				c[I][J]=a[I][J]+b[I][J];
		return c;
		
		
	}
	

	
	public static double[][] escalar(double[][] a,double k){
		
		double [][] x=new double[a.length][a[0].length];
		
		for(int I=0;I<a.length;I++)
			for(int J=0;J<(a[0].length);J++)
				x[I][J]=a[I][J];
		
		for(int I=0;I<x.length;I++)
			for(int J=0;J<(x[0].length);J++)
				x[I][J]=k*x[I][J];
		return x;
		
	}
	
	public static double[][] producto(double[][] a,double[][] b) throws Ematriz{
		
		
		
		if(a[0].length!=b.length)
			throw new Ematriz("error tamaño incosistente");
		
		double[][] c=new double[a.length][b[0].length];
		
		if(a.length==1 &&b.length==1 && a[0].length==1&& b[0].length==1){
			c[0][0]=a[0][0]*b[0][0];
			return c;
		}
			
		double temporal=0;
		for(int I=0;I<c.length;I++)
			for(int J=0;J<(c[0].length);J++){
				temporal=0.0;
				for(int K=0;K<b.length;K++){
					temporal+=(a[I][K]*b[K][J]);
					
				}				
					c[I][J]=temporal;				
			}
				
		return c;
	}
	
	public static void imprimir(double[][] a){
		if(a==null)
			return;
		
		for(int I=0;I<a.length;I++){
			System.out.println("");
			for(int J=0;J<(a[0].length);J++)
				if(Math.ceil(a[I][J])==Math.floor(a[I][J]))
					System.out.print((int)a[I][J]+" ");
				else
					System.out.print(a[I][J]+" ");
			}
		System.out.println();
	}
	
	public static double[][] potencia(double [][] a,int k)throws Ematriz{
		if(k<1)
			return potencia(inversa(a),Math.abs(k));
		
		if(k!=1){
			if(k%2==0)
				return producto(potencia(a, k/2), potencia(a, k/2 ));
			else
				return producto(a, potencia(a, k-1));
				
		}
		return a;		
	}
	
	public static double[][] transpuesta(double[][] a){
		
		double [][] c= new double[a[0].length][a.length];
		
		for(int I=0;I<a[0].length;I++)
			for(int J=0;J<(a.length);J++)
				c[I][J]=a[J][I];
		
		return c;
		
		
		
	}
	
	public static double determinante(double a[][])throws Ematriz {
		if(a.length!=a[0].length)
			throw new Ematriz("los determinantes son matrices cuadradas");
		
	double [][] x=new double[a.length][a[0].length];
		
		for(int I=0;I<a.length;I++)
			for(int J=0;J<(a[0].length);J++)
				x[I][J]=a[I][J];
		
			for(int k=0; k<x.length-1; k++) {
				for(int i=k+1; i<x[0].length; i++){
					for(int j=k+1; j<x.length; j++){
						x[i][j]-=x[i][k]*x[k][j]/x[k][k];
					}
				}
			}
		
		
		double deter=1.0;
		for(int i=0; i<x.length; i++){
            deter*=x[i][i];
        }
        return deter;
	}
	
	public static double[][]   inversa(double a[][]) throws Ematriz{
		if(determinante(a)==0 || a.length!=a[0].length)
			throw new Ematriz("no poseé matriz inversa");
		
		double [][] x=new double[a.length][a[0].length];
		
		for(int I=0;I<a.length;I++)
			for(int J=0;J<(a[0].length);J++)
				x[I][J]=a[I][J];
		
		
		double[][]	resp=new double [x.length][x.length];
		double[][]	resp2=new double[x.length][x.length];
		
			for(int i=0; i<x.length; i++) {
				resp[i][i]=1.0;
			}
		
			for(int k=0; k<x.length-1; k++) {
				for(int i=k+1; i<x.length; i++) {
					for(int s=0; s<x.length; s++) {
						resp[i][s]-=x[i][k]*resp[k][s]/x[k][k];
					}
					for(int j=k+1; j<x.length; j++) {
						x[i][j]-=x[i][k]*x[k][j]/x[k][k];
					}
				}
			}
		
			for(int s=0; s<x.length; s++){
            	resp2[x.length-1][s]=resp[x.length-1][s]/x[x.length-1][x.length-1];
            	for(int i=x.length-2; i>=0; i--){
                	resp2[i][s]=resp[i][s]/x[i][i];
                	for(int k=x.length-1; k>i; k--){
                    	resp2[i][s]-=x[i][k]*resp2[k][s]/x[i][i];
                	}
            	}
            	
            
        				
            	
        	}
       
        return resp2;
	}
	
	
	public static  double[][] copia(double [][] a){
		double [][] x=new double[a.length][a[0].length];
		
		for(int I=0;I<a.length;I++)
			for(int J=0;J<(a[0].length);J++)
				x[I][J]=a[I][J];
		return x;
	}

	

}
