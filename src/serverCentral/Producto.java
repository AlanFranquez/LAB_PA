package serverCentral;

public class Producto {
	private String nombre, descripcion;
	private float precio
	private Integer numRef;
	private String[] especificaciones;
	private String[] imagenes; // Chequear esto
	
	// Constructor:
	public Producto(String nombre, String descripcion, float precio, Integer numRef) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.numRef = numRef;
	}

	public agregarImagen(string img) {
		imagenes.
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
