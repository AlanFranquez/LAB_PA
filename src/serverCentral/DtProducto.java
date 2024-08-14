package serverCentral;

public class DtProducto {
	private String nombre, descripcion;
	private float precio;
	private Integer numRef;
	
	// Constructor:
	public DtProducto(String nombre, String descripcion, float precio, Integer numRef) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.numRef = numRef;
	}
	
	// Getters:
	public String getNombre() {
		return nombre;
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
}
