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
	private String especificaciones;
	//private Integer stock;
	private File imagenes;
	
	// Constructor:
	public Producto(String nombre, String descripcion, float precio, Integer numRef, String especificaciones, Proveedor prov, int stock) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.numRef = numRef;
		this.especificaciones = especificaciones;
		this.categorias = new HashMap<>();
		this.proveedor = prov;
		//this.stock = stock;
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
	
	public Map<String, Categoria> getCategorias() {
		return this.categorias;
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

	public String getEspecificaciones() {
		return this.especificaciones;
	}
	
	public DtProducto crearDT() {
		return new DtProducto(this.getNombre(), this.getDescripcion(), this.getPrecio(), this.getNumRef(), this.getEspecificaciones(), this.getProveedor());
	}
}