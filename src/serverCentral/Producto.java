package serverCentral;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Producto {
	private Map<String, Categoria> categorias;
	private Comentario[] comentarios;
	private Proveedor proveedor;
	
	private String nombre, descripcion;
	private float precio;
	private Integer numRef;
	private String especificaciones;
	private List<File> imagenes;
	
	// Constructor:
	public Producto(String nombre, String descripcion, float precio, Integer numRef, String especificaciones, Proveedor prov, int stock) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.numRef = numRef;
		this.especificaciones = especificaciones;
		this.categorias = new HashMap<>();
		this.proveedor = prov;
		this.imagenes = new ArrayList<>();
	}

	// Hacer una lista de imágenes en vez de una única
	public void agregarImagen(File img) {
		this.imagenes.add(img);
	}
	
	public List<File> getImagenes() {
		return this.imagenes;
	}
	
	public void agregarComentario(Comentario com) {
		comentarios[comentarios.length] = com;
	}
	public Comentario[] getComentarios() {
		return comentarios;
	}
	
	
	public void agregarNuevasImagenes(List<File> imagenes) {
		this.imagenes = null;
		this.imagenes = imagenes;
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
	
	public String[] categoriasProducto() {
    	int contador = 0;
    	String[] arrString = new String[contador];
    		
    	for(Map.Entry<String, Categoria> entry : this.categorias.entrySet()) {
    		
    		Categoria c = entry.getValue();
    		Cat_Producto cProducto = (Cat_Producto) c;
    		arrString[contador++] = cProducto.getNombre();
    		
    	
    		Cat_Padre cPadre = cProducto.getPadre();
    		
    		while(cPadre.traerPadre() != null) {
    			arrString[contador++] = cPadre.getNombre();
    			
    			
    			
    			cPadre = cPadre.traerPadre();
    		}
    	}
    	
    	return arrString;
    }
	
	public DtProducto crearDT() {
		String tab = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;•";
		String catStr = "";
		if(this.categorias.isEmpty()) {
			catStr = "El producto no tiene categorias asignadas";
		}
		for (Categoria cat : this.categorias.values()) {	
			catStr = catStr + "<br>" + tab + cat.getNombre();				
		}
		
		
		catStr = catStr + "</html>";
		return new DtProducto(this.getNombre(), this.getDescripcion(), this.getPrecio(), this.getNumRef(), this.getEspecificaciones(), this.getProveedor(), this.categorias, this.getImagenes());
	}
}