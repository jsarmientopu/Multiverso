package structure;


public class Universo {
	
	private String nombre;
	private ArrayList<String> personas;
	private Universo next;
	private Universo nextcapa;
	
	
	public Universo(String nombre) {
		super();
		this.nombre = nombre;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public ArrayList<String> getPersonas() {
		return personas;
	}
	public void setPersonas(ArrayList<String> personas) {
		this.personas = personas;
	}
	public Universo getNext() {
		return next;
	}
	public void setNext(Universo next) {
		this.next = next;
	}
	public Universo getNextcapa() {
		return nextcapa;
	}
	public void setNextcapa(Universo nextcapa) {
		this.nextcapa = nextcapa;
	}
	
	
	

}
