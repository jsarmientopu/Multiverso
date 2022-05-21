package structure;

public class Persona {

	private String nombre;
	private Universo origen;
	
	public Persona(String nombre) {
		super();
		this.nombre = nombre;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Universo getOrigen() {
		return origen;
	}
	public void setOrigen(Universo origen) {
		this.origen = origen;
	}
	
	
	
	
}
