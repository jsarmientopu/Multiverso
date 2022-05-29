package structure;


public class Universo {
	
	private String nombre;
	private ArrayList<String> personas;
	private Universo next;
	private Universo nextcapa;
	private int id;
	private int conexiones;
	
	public Universo(String nombre) {
		this.nombre = nombre;
		this.next = null;
		this.nextcapa = null;
	}
	public Universo() {
		this.nombre = null;
		this.next = null;
		this.nextcapa = null;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getConexiones() {
		return conexiones;
	}
	public void setConexiones(int conexiones) {
		this.conexiones = conexiones;
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
