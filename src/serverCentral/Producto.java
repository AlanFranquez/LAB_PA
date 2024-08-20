package serverCentral;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Producto {
	private Map<String, Categoria> categorias;
	private Comentario[] comentarios;
	private Proveedor proveedor;
	
	private String nombre, descripcion;
	private float precio;
	private Integer numRef;
	private String[] especificaciones;
	private File imagenes; // Chequear esto----Carlos:Agregue un tipo file, no se como funciona para guardarlo pero creo que es el tipo de variable correcto
	// Buscando ví que tambien existe el tipo ImageIcon que parece puede funcionar con la interfaz. Habría que esperar a tener una interfaz para ver si anda File.
	
	
	// Constructor:
	// Tal vez al crear al producto conbendría asignarle directamente el proveedor y categorias en vez de hacerlo por separado
	public Producto(String nombre, String descripcion, float precio, Integer numRef, String[] especificaciones) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.numRef = numRef;
		this.especificaciones = especificaciones;
		this.categorias = new HashMap<>();
	}

	// Hacer una lista de imágenes en vez de una única
	public void agregarImagen(File img) {
		imagenes = img;
	}
	public File getImagen() {
		return imagenes;
	}
	
	
	// No creo que esto funcione
	public void agregarComentario(Comentario com) {
		comentarios[comentarios.length] = com;
	}
	public Comentario[] getComentarios() {
		return comentarios;
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
	
	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor prov) {
		this.proveedor = prov;
	}

	public String[] getEspecificaciones() {
		return especificaciones;
	}
	
}
