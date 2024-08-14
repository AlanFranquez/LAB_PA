package serverCentral;

import java.util.HashMap;
import java.util.Map;

public class Cat_Padre extends Categoria{
	private Map<String, Categoria> hijos;
	
	// Constructor:
	public Cat_Padre(String nombre) {
		super(nombre, "Padre");
		hijos = new HashMap<String, Categoria>();
	}
	
	// Getter:
	public Map<String, Categoria> getHijos() {
		return hijos;
	}

	// Funcionalidad:
	public void agregarHijo(Categoria cat) {
		hijos.put(cat.getNombre(), cat);
	}
}
