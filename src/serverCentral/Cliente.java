package serverCentral;
import java.util.Map;
import java.util.HashMap;

public class Cliente extends Usuario {
    private Map<Integer, OrdenDeCompra> listaCompras;
    private Map<Integer, Comentario> listaComentarios;
    // Constructor
    public Cliente(String nombre, String nick, String apellido, String correo, DTFecha fecha) {
        super(nombre, nick, apellido, correo, fecha, "cliente"); // constructor de Usuario
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
    //cosas OrdenCompra
    public void agregarCompra(int num, OrdenDeCompra Orden) {
        listaCompras.put(num, Orden);
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

    //opers
    /*public Set<Integer> getAllOrdenes() {
        Set<Integer> res = new HashSet<>();
        for (OrdenDeCompra ordenActual : listaCompras.values()) {
            res.add(ordenActual.getId());
        }
        return res;
    }*/
    
    public DTCliente crearDt() {
        return new DTCliente(this.getNombre(), getNick(), getApellido(), getCorreo(), getNacimiento(), getImagen(), getCompras());
    }
}
