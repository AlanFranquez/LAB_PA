package serverCentral;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DtProducto {
	private String nombre, descripcion;
	private float precio;
	private Integer numRef;
	private String especs;
	private Proveedor proveedor;
	private Map<String, Categoria>categorias;
	
	// Constructor:
	public DtProducto(String nombre, String descripcion, float precio, Integer numRef, String especs, Proveedor prov, Map<String, Categoria> cat) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.numRef = numRef;
		this.especs = especs;
		this.proveedor = prov;
		this.categorias = cat;
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
	
	public Map<String, Categoria> getCategorias() {
		return categorias;
	}
	

	public List<Categoria> categoriasProducto() {
	    List<Categoria> listacats = new ArrayList<>();
	    
	    for (Map.Entry<String, Categoria> entry : this.categorias.entrySet()) {
	        Categoria c = entry.getValue();
	        
	        if (c instanceof Cat_Producto) {
	            Cat_Producto cProducto = (Cat_Producto) c;
	            listacats.add(cProducto);
	            
	            Cat_Padre cPadre = cProducto.getPadre();
	            
	            // Recorrer la cadena de padres
	            while (cPadre != null) {
	                listacats.add(cPadre);
	                
	                // Obtener el siguiente padre
	                cPadre = cPadre.traerPadre();
	            }
	        } else {
	            // Manejar el caso en que c no es una instancia de Cat_Producto
	            System.out.println("Categoria no es una instancia de Cat_Producto: " + c.getClass().getName());
	        }
	    }
	    
	    return listacats;
	}


}
