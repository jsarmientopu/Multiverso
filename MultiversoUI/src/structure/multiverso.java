package structure;

import java.util.Random;
import structure.Universo;

public class multiverso {

	private Universo subida;
	private Universo bajada;
	private int capas=-1;
	
	public multiverso() {
		
		Universo sub = new Universo("UniversoSubida");
		Universo baj = new Universo();
		this.subida = sub;
		this.bajada = baj;
		subida.setId("0");
		bajada.setId("0");
		sub.setNext(baj);
		baj.setNext(sub);
		
	}
	
	public Universo getSubida() {
		return this.subida;
	}
	
	public Universo getBajada() {
		return this.bajada;
	}
	
	public void add(String nombre, String con) {
		
		Universo newUniverse = new Universo(nombre);
		Universo conexion = buscar(con);
		int id = Integer.valueOf(conexion.getId().charAt(0));
		Universo current = subida;
		int id2 = Integer.valueOf(subida.getId().charAt(0));
		while(id2 != id) {
			current = current.getNextcapa();
			id2 = Integer.valueOf(current.getId().charAt(0));
		}
		while(current.getNext()!=conexion) {
			current = current.getNext();
		}
		if(current.getNombre() == null) {
			current.setNombre(nombre);
		}else {
			newUniverse.setNext(conexion);
			current.setNext(newUniverse);
		}
		
	}
	
	public void delete(String nombre) {
		
		Universo raiz = subida;
		Universo deleted = buscar(nombre);
		if(deleted.getNextcapa()!= null) {
			boolean bandera = false;
			if((deleted.equals(subida))&&(deleted.getNext()!=null)) {
				this.subida = subida.getNextcapa();
				restaurarBajada();
				bandera = true;
			}else {
				for(int i = 0; i<=capas; i++) {
					Universo current = raiz;
					if(current.getNextcapa().getNombre() == nombre) {
						bandera = false;
						if(contarUniversos(current.getNextcapa())<=2) {
							current.getNextcapa().getNext().setNext(current.getNext().getNext());
							current.setNext(current.getNextcapa().getNext());
							arreglarBajada(current.getNextcapa());
							if(current.getNextcapa().getNextcapa()!=null) {
								current.setNextcapa(current.getNextcapa().getNextcapa());
							}else {
								current.setNextcapa(null);
							}
						}else {
							Universo current2 =current.getNextcapa();
							while(current2.getNextcapa()!= null) {
								current2 = current2.getNext();
							}
							current2.setNextcapa(current.getNextcapa().getNextcapa());
							current.setNextcapa(current2);
						}
						break;
					}
					raiz = raiz.getNextcapa();
				}
			}
			if(bandera) {
				if(contarUniversos(deleted)<=2) {
					if(deleted.equals(bajada)) {
						if(subida.getNextcapa()!=null) {
							restaurarBajada();
							this.subida = deleted.getNext();
						}
					}else {
						if(deleted.getNext().getNextcapa()!=null) {
							arreglarBajadaCapa(deleted.getNext().getNextcapa());
							arreglarSubida(deleted.getNext());
						}else {
							arreglarSubidaBajaCapa(deleted.getNext());
						}
					}
				}else {
					arreglarBajada(deleted);
				}
			}
		}else {
			Universo current = deleted;
			while(current.getNext().equals(deleted)==false) {
				current = current.getNext();
			}
			current.setNext(current.getNext().getNext());
		}
		
		
		
	}

	public Universo add(String nombreUniverso, int capa) {
		
		Universo universo = new Universo(nombreUniverso);
		Universo relleno = new Universo();
		Universo current = subida;
		if(capas == -1) {
			subida.setNombre(nombreUniverso);
			capas ++;
			return subida;
		}else if(capa > capas) {
			for(int i = 0; i< capas; i++) {
				current = current.getNextcapa();
			}
			current.setNextcapa(universo);
			universo.setNext(relleno);
			universo.setId(String.valueOf(Integer.valueOf(current.getId())+1));
			relleno.setNext(universo);
			relleno.setId(universo.getId());
			Universo current2 = current;
			while(current2.getNextcapa()==null) {
				current2 = current2.getNext();
			}
			relleno.setNextcapa(current2);
			capas ++;
		}else {;
			for(int i = 0; i<capa ; i++) {
				current = current.getNextcapa();
			}
			Universo universoRaizcapa = current;
			while((current.getNext() != universoRaizcapa)&&(current.getNombre()!=null)) {
				current = current.getNext();
			}
			if(current.getNombre() == null) {
				current.setNombre(nombreUniverso);
				return current;
			}else {
				universo.setNext(universoRaizcapa);
				universo.setId(universoRaizcapa.getId());
				current.setNext(universo);
			}
		}
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
		
		Universo current = subida;
		for(int i = 0; i< capa;i++) {
			current = current.getNextcapa();
		}
		for(int i2 = 0; i2 < valor; i2++) {
			current = current.getNext();
		}
		return current;
		
	}
	
	public Universo viajarSiguiente(boolean cambiarCapa) {
		
		Universo current = subida;
		if(cambiarCapa) {
			return current.getNextcapa();
		}else {
			return current.getNext();
		}
		
	}
	
	public Universo buscar(String nombre) {
		
		Universo raiz = subida;
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
		
		Universo raizCapa = subida.getNextcapa();
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
		Universo raiz = subida;
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
	
	public static int contarUniversos(Universo current) {
		int contador = 0;
		Universo universe = current;
		while(current.equals(universe)== false) {
			current = current.getNext();
			contador++;
		}
		return contador;
	}
	
	public static void arreglarConexion(Universo raiz, Universo universe) {
		Universo current = universe;
		while(current.getNext().equals(raiz)==false) {
			current = current.getNext();
		}
		current.setNext(universe);
	}
	
	public void restaurarBajada() {
		Universo current = subida.getNextcapa().getNext();
		while(current.getNextcapa()==null) {
			current = current.getNext();
		}
		this.bajada= current;
	}
	
	public void arreglarBajadaCapa(Universo universe) {
		Universo current = universe.getNext();
		while(current.getNextcapa()==null) {
			current = current.getNext();
		}
		current.setNextcapa(current.getNextcapa().getNextcapa());
	}
	
	public void arreglarBajada(Universo universe) {
		if(universe.getNextcapa() == null) {
			universe.getNext().setNextcapa(null);
		}else {
			Universo current = subida;
			for(int i=0; i< capas; i++) {
				boolean bandera = false;
				Universo current2 = current;
				while(current.equals(current2)==false) {
					if(current2.equals(universe)) {
						bandera = true;
						break;
					}
					current2 = current2.getNext();
				}
				current = current.getNextcapa();
				if(bandera) {
					break;
				}
			}
			while((current.getNext().getNextcapa()==null)) {
				current = current.getNext();
			}
			Universo a = universe;
			while(a.getNextcapa()!=null) {
				a.getNext();
			}
			a.setNextcapa(universe.getNextcapa());
			current.setNextcapa(a);
		}
		
	}
	
	public void arreglarSubida(Universo universe) {
		Universo current = subida;
		while(current.getNextcapa().equals(universe)==false) {
			current = current.getNextcapa();
		}
		current.setNextcapa(current.getNextcapa().getNextcapa());
	}

	public void arreglarSubidaBajaCapa(Universo universe) {
		Universo current = subida;
		while(current.getNextcapa().equals(universe)==true) {
			current = current.getNextcapa();
		}
		universe.setNext(current.getNext().getNext());
		current.setNext(universe);
	}
	
}


