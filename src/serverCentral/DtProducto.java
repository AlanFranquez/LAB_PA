package serverCentral;

import java.io.File;
import java.util.List;

public class DtProducto {
	private String nombre, descripcion;
	private float precio;
	private Integer numRef;
	private String especs;
	private Proveedor proveedor;
	private String categorias;
	List<File> imagenes;
	
	// Constructor:
	public DtProducto(String nombre, String descripcion, float precio, Integer numRef, String especs, Proveedor prov, String cat, List<File> imagenes) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.numRef = numRef;
		this.especs = especs;
		this.proveedor = prov;
		this.categorias = cat;
		this.imagenes = imagenes;
	}
	
	// Getters:
	public String getNombre() {
		return nombre;
	}
	
	public String getNombreProveedor() {
		return this.proveedor.getNombre();
	}
	
	public String getNicknameProveedor(){
		return this.proveedor.getNick();
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	public float getPrecio() {
		return precio;
	}
	public Integer getNumRef() {
		return numRef;
	}
	
	public String getEspecs() {
		return especs;
	}
	
	public String getCategorias() {
		return categorias;
	}
	
	public void agregarImagen(File img) {
		this.imagenes.add(img);
	}
	
	public List<File> getImagenes() {
		return this.imagenes;
	}

}
