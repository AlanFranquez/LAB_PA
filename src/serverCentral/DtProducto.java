package serverCentral;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DtProducto {
	private String nombre, descripcion;
	private float precio;
	private Integer numRef;
	private String especs;
	private Proveedor proveedor;
	
	// Constructor:
	public DtProducto(String nombre, String descripcion, float precio, Integer numRef, String especs, Proveedor prov) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.numRef = numRef;
		this.especs = especs;
		this.proveedor = prov;
	}
	
	// Getters:
	public String getNombre() {
		return nombre;
	}
	
	public String getNombreProveedor() {
		return this.proveedor.getNombre();
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
	
	
	

}
