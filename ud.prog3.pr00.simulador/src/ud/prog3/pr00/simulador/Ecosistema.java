package ud.prog3.pr00.simulador;

import java.util.ArrayList;

public class Ecosistema {

	protected ArrayList<ElementoEcosistema>listaEEs;
	private static Ecosistema Crepusculo = new Ecosistema(new ArrayList<ElementoEcosistema>());
	
	public Ecosistema(ArrayList<ElementoEcosistema> listaEEs) {
		super();
		this.listaEEs = new ArrayList<ElementoEcosistema>();
		
	}
	
	public static int distancia(ElementoEcosistema uno, ElementoEcosistema dos) {
		return (int) Math.sqrt(Math.pow(dos.getPosicion().x-uno.getPosicion().x,2)+Math.pow(dos.getPosicion().y-uno.getPosicion().y, 2));
	}
	
	public static Ecosistema getMundo() {
		return Crepusculo;
	}
	
	public ArrayList<ElementoEcosistema> getElementos(){
		return listaEEs;
				
	}
	

}
