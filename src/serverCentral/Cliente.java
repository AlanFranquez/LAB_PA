package serverCentral;

public class Cliente extends Usuario {
    private Map<Integer, OrdenDeCompra> listaCompras;
    private Map<Integer, Comentario> listaComentarios;
    // Constructor
    public Cliente(String Nom, String Ni, String Ape, String Cor, Date Naci) {
        super(Nom, Ni, Ape, Cor, Naci); // constructor de Usuario
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
    //opers
    public Set<Integer> getAllOrdenes() {
        Set<Integer> res = new HashSet<>();
        for (OrdenDeCompra ordenActual : listaCompras.values()) {
            res.add(ordenActual.getId());
        }
        return res;
    }
    
    public dtCliente crearDt() {
        return new dtCliente(getNombre(), getNick(), getApellido(), getCorreo(), getNacimiento(), getImagen());
    }
}
