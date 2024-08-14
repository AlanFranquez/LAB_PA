package serverCentral;

public class Cliente extends Usuario {
    private List<OrdenDeCompra> listaCompras;
    private List<Comentario> listaComentarios;
    // Constructor
    public Cliente(String Nom, String Ni, String Ape, String Cor, Date Naci) {
        super(Nom, Ni, Ape, Cor, Naci); // constructor de Usuario
        this.listaCompras = new ArrayList<>();
        this.listaComentarios = new ArrayList<>();
    }
    // gets, sets
    public List<OrdenDeCompra> getCompras() {
        return listaCompras;
    }

    public List<Comentario> getComentarios() {
        return listaComentarios;
    }
    //opers
    public Set<Integer> getAllOrdenes() {
        Set<Integer> RES = new HashSet<>();
        for (OrdenDeCompra ordenActual : listaCompras) {
            RES.add(ordenActual.getId());
        }
        return RES;
    }
}
