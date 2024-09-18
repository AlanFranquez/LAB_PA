package serverCentral;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Cliente extends Usuario {
    private Map<Integer, OrdenDeCompra> listaCompras;
    private Map<Integer, Comentario> listaComentarios;
    
    // Constructor
    public Cliente(String nombre, String nick, String apellido, String correo, DTFecha fecha) {
        super(nombre, nick, apellido, correo, fecha, "cliente", contrasena);
        this.listaCompras = new HashMap<>();
        this.listaComentarios = new HashMap<>();
    }
    // gets, sets
    public Map<Integer, OrdenDeCompra> getCompras() {
        return listaCompras;
    }

    public Map<Integer, Comentario> getComentarios() {
        return listaComentarios;
    }

    public void agregarCompra(OrdenDeCompra orden) {
        listaCompras.put(orden.getNumero(), orden);
    }
    public OrdenDeCompra obtenerOrden(int num) {
        return listaCompras.get(num);
    }
    public void eliminarOrden(int num) {
        listaCompras.remove(num);
    }
    public boolean existeOrden(int num) {
        return listaCompras.containsKey(num);
    }
    public int cantCompras() {
        return listaCompras.size();
    }
   
    // Mas que un set de integers creo que debería de ser un arreglo de dtOrdenCompra
    public Set<Integer> getAllOrdenes() {
        Set<Integer> res = new HashSet<>();
        for (OrdenDeCompra ordenActual : listaCompras.values()) {
            res.add(ordenActual.getNumero());
        }
        return res;
    }
    
    public DTCliente crearDt() {
        return new DTCliente(this.getNombre(), this.getNick(), this.getApellido(), this.getCorreo(), this.getNacimiento(), this.getImagen(), this.getCompras());
    }
}

