package serverCentral;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Producto {
	private Map<String, Categoria> categorias;
	private String nombre, descripcion;
	private float precio;
	private Integer numRef;
	private String[] especificaciones;
	private File imagenes; // Chequear esto----Carlos:Agregue un tipo file, no se como funciona para guardarlo pero creo que es el tipo de variable correcto
	
	// Constructor:
	public Producto(String nombre, String descripcion, float precio, Integer numRef, String[] especificaciones) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.numRef = numRef;
		this.especificaciones = especificaciones;
		this.categorias = new HashMap<>();
	}

	public Producto(String titulo, Integer numRef2, String descripcion2, String[] especificacionesArray, Float precio2,
			Usuario proveedor) {
		// TODO Auto-generated constructor stub
	}

	public void agregarImagen(File img) {
		imagenes = img;
	}
	public void agregarCategorias(Categoria cat) {
		categorias.put(cat.getNombre(), cat);
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
