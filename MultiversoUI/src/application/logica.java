package application;

import structure.ArrayList;
import structure.Universo;
import structure.multiverso;

public class logica {

	public static void crear() {
		multiverso multi = new multiverso();
		VistaControlador.setMulti(multi,multi.getSubida());
		Universo simpsoms = multi.add("simpsoms", 0);
		simpsoms.setPersonas(new ArrayList("bart", "lisa", "homero", "simp"));
		Universo PF = multi.add("Phineas y Ferb", 0);
		PF.setPersonas(new ArrayList("phineas", "perry", "mama", "ferb"));
		Universo DC = multi.add("DC comics", 1);
		DC.setPersonas(new ArrayList("acuaman", "flash", "linterna", "superman"));
		Universo HP = multi.add("Harry Potter", 1);
		HP.setPersonas(new ArrayList("harry", "ron", "hagrid", "hermiony"));
		
		
		
		Universo current = multi.getSubida().getNext();
		System.out.println(multi.getSubida().getNombre());
		System.out.println(HP.getNextcapa());
		Universo raiz = multi.getSubida();
		
		
		multi.add("pep", "Harry Potter");
		System.out.println(DC.getNext().getNombre());
		System.out.println(DC.getNext().getNext().getNombre());
		System.out.println("FINAL");
		
	}
	
	
}
