package serverCentral;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Cat_Producto extends Categoria{
private Map<String, Producto> productos;
	
	// Constructor:
	public Cat_Producto(String nombre) {
		super(nombre, "Producto");
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


	public void agregarProducto(Producto prod) {
		productos.put(prod.getNumRef(), prod);
	}
}