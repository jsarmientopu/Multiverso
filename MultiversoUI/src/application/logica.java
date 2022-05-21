package application;

import structure.ArrayList;
import structure.Universo;
import structure.multiverso;

public class logica {

	public static void crear() {
		multiverso multi = new multiverso();
		VistaControlador.setMulti(multi,multi.getRaiz());
		Universo simpsoms = multi.add("simpsoms", 0);
		simpsoms.setPersonas(new ArrayList("bart", "lisa", "homero", "simp"));
		Universo PF = multi.add("Phineas y Ferb", 0);
		PF.setPersonas(new ArrayList("phineas", "perry", "mama", "ferb"));
		Universo DC = multi.add("DC comics", 0);
		DC.setPersonas(new ArrayList("acuaman", "flash", "linterna", "superman"));
		Universo HP = multi.add("Harry Potter", 0);
		HP.setPersonas(new ArrayList("harry", "ron", "hagrid", "hermiony"));
	}
	
	
}
