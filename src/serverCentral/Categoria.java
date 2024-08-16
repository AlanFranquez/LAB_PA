package serverCentral;

import java.util.Map;

public class Categoria {
	private String nombre;
	private String tipo;
	
	
	// Constructor:
	public Categoria(String nombre, String tipo) {
		this.nombre = nombre;
		this.tipo = tipo;
	}
	
	public Categoria(String nombre, String tipo, Categoria cat) {
		this.nombre = nombre;
		this.tipo = tipo;
	}

	// Getters y Setters:
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
