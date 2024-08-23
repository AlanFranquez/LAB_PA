package serverCentral;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Cat_Producto extends Categoria{
private Map<Integer, Producto> productos;
Cat_Padre padre;
	
	// Constructor:
	public Cat_Producto(String nombre) {
		super(nombre, "Producto");
		padre = null;
		productos = new HashMap<Integer, Producto>();
	}
	
	// Funcionalidad:
	public DtProducto[] listarProductos() {
	    if (productos.isEmpty()) {
	        return null;
	    } else {
	        Collection<Producto> prods = productos.values();
	        DtProducto[] lista = new DtProducto[prods.size()];
	        int i = 0;
	        for (Producto prod : prods) {
	            lista[i] = new DtProducto(prod.getNombre(), prod.getDescripcion(), prod.getPrecio(), prod.getNumRef());
	            i++;
	        }
	        return lista;
	    }
	}
	
	
	
	public String obtenerPadre() {
		if(this.padre == null) {
			return null;
		}
		return this.padre.getNombre();
	}
	
	public void setPadre(Cat_Padre cat) {
		this.padre = cat;
	}
	
	
	// Esto para que?
	// Debería de bastar con obtener los dt de los productos
	// Hay que tener cuidado de no romper el principio de las capas
	public Map<Integer, Producto> getProductos(){
		return this.productos;
	}

	public void agregarProducto(Producto prod) {
		productos.put(prod.getNumRef(), prod);
	}
	
	
}