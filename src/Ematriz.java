
@SuppressWarnings("serial")
public class Ematriz extends Exception {
	private String mensaje="";
	public Ematriz(String mens){
		super("exception");
		mensaje=mens;
	
	}
	
	public String getmensaje(){
		return mensaje;
	}
	

}
