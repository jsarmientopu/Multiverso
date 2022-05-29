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
		subida.setId(0);
		bajada.setId(0);
		sub.setNext(baj);
		baj.setNext(sub);
		
	}
	
	public Universo getSubida() {
		return this.subida;
	}
	
	public Universo getBajada() {
		return this.bajada;
	}
	
	public Universo add(String nombre, Universo conexion) {
		
		Universo newUniverse = new Universo(nombre);
		int id = conexion.getId();
		newUniverse.setId(conexion.getId());
		Universo current = subida;
		int id2 = subida.getId();
		while(id2 != id) {
			current = current.getNextcapa();
			id2 = current.getId();
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
		return newUniverse;
		
	}
	
	public void delete(Universo deleted) {
		
		Universo raiz = subida;
		boolean bandera = false;
		System.out.println("INICIO ELIMINACION");
		if((deleted.equals(subida))&&(deleted.getNextcapa()!=null)) {
			if(contarUniversos(subida)<=2){
				if(contarUniversos(subida.getNextcapa())>=2) {
					this.subida = subida.getNextcapa();
					restaurarBajada();
					bandera = false;	
				}
			}else {
				this.subida = subida.getNext();
			}
		}else {
			for(int i = 0; i<=capas; i++) {
				Universo current = raiz;
				if(current.getNextcapa() == deleted) {
					bandera = false;
					if(contarUniversos(current.getNextcapa())<=2) {
						current.getNextcapa().getNext().setNext(current.getNext());
						current.setNext(current.getNextcapa().getNext());
						current.getNextcapa().getNext().setId(current.getId());
						arreglarBajada(current.getNextcapa());
						if(current.getNextcapa().getNextcapa()!=null) {
							current.setNextcapa(current.getNextcapa().getNextcapa());
						}else {
							current.setNextcapa(null);
						}
						System.out.println("SALGO");
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
		if(bandera &&(deleted.getNextcapa()!=null)) {
			if(contarUniversos(deleted)<=2) {
				if(deleted.equals(bajada)) {
					if((subida.getNextcapa()!=null)&&(contarUniversos(subida.getNextcapa())>2)) {
						restaurarBajada();
						this.subida = this.subida.getNextcapa();
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
		}else if(bandera){
			Universo current = deleted;
			while(current.getNext().equals(deleted)==false) {
				current = current.getNext();
			}
			current.setNext(current.getNext().getNext());
		}
		System.out.println("FINAL ELIMINACIÓN");
		
		
		
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
			universo.setId(current.getId()+1);
			relleno.setNext(universo);
			relleno.setId(current.getId()+1);
			Universo current2 = current.getNext();
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
				System.out.println(nombreUniverso);
				return current;
			}else {
				universo.setNext(universoRaizcapa);
				universo.setId(universoRaizcapa.getId());
				current.setNext(universo);
			}
		}
		return universo;
		
	}
	
	
	
	public Universo viaje(Universo current, Universo llegada) {
		System.out.println("----------------------------------------------------------------------");
		System.out.println("VIAJE");
		Universo current2 = current;
		if(current.getId()!=llegada.getId()) {
			if(current.getId()>llegada.getId()) {
				Universo inc = buscarBajada(current.getId());
				System.out.println("BAJADA= "+inc.getNombre());
				while(current2!=inc) {
					System.out.println(current2.getNombre());
					current2 = current2.getNext();
				}
			}else{
				Universo dec = buscarSubida(current.getId());
				while(current2!=dec) {
					System.out.println(current2.getNombre());
					current2 = current2.getNext();
				}
			}

			System.out.println("BAJADA= "+current2.getNombre());
			while(current2.getId() != llegada.getId()) {
				System.out.println(current2.getNombre());
				current2 = current2.getNextcapa();
			}
		}
		while(current2!=llegada) {
			System.out.println(current2.getNombre());
			current2 = current2.getNext();
		}
		return current2;
	}
	
	public Universo buscarSubida(int id) {
		Universo current = subida;
		while(current.getId()!=id) {
			current = current.getNextcapa();
		}
		return current;
	}
	public Universo buscarBajada(int id) {
		Universo current = subida;
		while(current.getId() != id) {
			current = current.getNextcapa();
		}
		current = current.getNext();
		while(current.getNextcapa()==null) {
			current = current.getNext();
		}
		return current;
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
			arreglarBajadaCapa(universe);
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
	
	public ArrayList<Universo> universosMulti(){
		ArrayList<Universo> a = new ArrayList<Universo>();
		Universo current = subida.getNext();
		Universo raiz = subida;
		a.add(subida);
		while(current!=null) {
			a.add(current);
			if(current.getNext().equals(raiz)) {
				current = current.getNext().getNextcapa();
				raiz = current;
			}else {
				current = current.getNext();
			}
		}
		return a;
	}
	
}


