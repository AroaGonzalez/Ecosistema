package ud.prog3.pr00.simulador;

import java.awt.Dimension;
import java.awt.Point;

public class Prueba {

	public static void main(String[] args) {
		
		Agua a1 = new Agua("aguamar", new Dimension(), new Point(10, 20));
		ColoniaAbejas ca1 = new ColoniaAbejas("aguita", new Dimension(), new Point(10, 20), 200);
		PlantacionFlores pf1 = new PlantacionFlores("aguosa", new Dimension(), new Point(10, 20), 20);
		
		Ecosistema.getMundo().getElementos().add(a1);
		Ecosistema.getMundo().getElementos().add(ca1);
		Ecosistema.getMundo().getElementos().add(pf1);
		
		Thread hilo = new Thread(new Runnable() {

			public void run() {
				
				while(true){
					try {
						Thread.sleep(1000);
						for (ElementoEcosistema ee : Ecosistema.getMundo().getElementos()) {
							if (ee instanceof ColoniaAbejas) {
								((ColoniaAbejas) ee).evoluciona(1);
								System.out.println(ee);
							}
							if (ee instanceof PlantacionFlores) {
								((PlantacionFlores) ee).evoluciona(1);
								System.out.println(ee);
							}
							if (ee instanceof Agua) {
								System.out.println(ee);
							} 
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
		});
		hilo.start();
	}

}
