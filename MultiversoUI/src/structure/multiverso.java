package structure;

import java.util.Random;

public class multiverso {

	private Universo universo1;
	private int universos;
	private int capas=0;
	
	public multiverso() {
		
		Universo raiz = new Universo("UniversoRaiz");
		this.universo1 = raiz;
		raiz.setNext(raiz);
		
	}
	
	public Universo getRaiz() {
		return this.universo1;
	}
	
	public Universo add(String nombreUniverso, int capa) {
		
		Universo universo = new Universo(nombreUniverso);
		Universo current = universo1;
		if(capa > capas) {
			for(int i = 0; i< capas; i++) {
				current = current.getNextcapa();
			}
			current.setNextcapa(universo);
			capas ++;
		}else {;
			for(int i = 0; i<capa ; i++) {
				current = current.getNextcapa();
			}
			Universo universoRaizcapa = current;
			while(current.getNext() != universoRaizcapa) {
				current = current.getNext();
			}
			universo.setNext(universoRaizcapa);
			current.setNext(universo);
		}
		arreglarCon();
		universos ++;
		return universo;
	}
	
	public Universo viajar(Universo current, int a) {
		
		if((a<=0)||(a>2)) {
			return null;
		}else {
			if(a == 1) {
				return current.getNext();
			}else {
				return current.getNextcapa();
			}
		}
		
	}
	
	public 	Universo viajar2(int capa, int valor) {
		
		Universo current = universo1;
		for(int i = 0; i< capa;i++) {
			current = current.getNextcapa();
		}
		for(int i2 = 0; i2 < valor; i2++) {
			current = current.getNext();
		}
		return current;
		
	}
	
	public Universo viajarSiguiente(boolean cambiarCapa) {
		
		Universo current = universo1;
		if(cambiarCapa) {
			return current.getNextcapa();
		}else {
			return current.getNext();
		}
		
	}
	
	public Universo buscar(String nombre) {
		
		Universo raiz = universo1;
		for(int i = 0; i<=capas; i++) {
			Universo current = raiz;
			while(current.getNext() != raiz) {
				if(current.getNombre() == nombre) {
					return current;
				}
				current = current.getNext();
			}
			if(current.getNombre() == nombre) {
				return current;
			}
			raiz = raiz.getNextcapa();
		}
		return null;
		
	}
	
	public void arreglarCon() {
		
		Universo raizCapa = universo1.getNextcapa();
		for(int i = 0; i<capas; i++) {
			Universo current = raizCapa;
			int contador= 0;
			while(current.getNext() != raizCapa) {
				current = current.getNext();
				if(contador %2 != 0) {
					current.setNextcapa(viajar2(i, contador));
				}
				contador++;
			}
			raizCapa = raizCapa.getNextcapa();
		}
		Universo raiz = universo1;
		for(int i = 0; i<capas; i++) {
			Universo current = raiz;
			int contador= 0;
			while(current.getNext() != raiz) {
				current = current.getNext();
				if(contador %2 == 0) {
					current.setNextcapa(viajar2(i+1, contador));
				}
				contador++;
			}
			raiz = raiz.getNextcapa();
		}
		
	}
	
}
