package serverCentral;

import java.util.Map;

public class Producto {
	private Map<String, Categoria> categorias;
	private String nombre, descripcion;
	private float precio;
	private Integer numRef;
	private String[] especificaciones;
	private String[] imagenes; // Chequear esto
	
	// Constructor:
	public Producto(String nombre, String descripcion, float precio, Integer numRef, String[] especificaciones) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.numRef = numRef;
		this.especificaciones = especificaciones;
		imagenes = new String[10];
	}

	public void agregarImagen(String img) {
		imagenes[imagenes.length] = img;
	}
	public void agregarCategorias(Categoria cat) {
		categorias.put(cat.getNombre(), cat)
	}
	public void eliminarCategorias() {
		categorias.clear();
	}
	
	// Getters y Setters:
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public Integer getNumRef() {
		return numRef;
	}

	public void setNumRef(Integer numRef) {
		this.numRef = numRef;
	}
	


}
