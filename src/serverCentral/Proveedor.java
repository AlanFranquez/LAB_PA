package serverCentral;

public class Proveedor extends Usuario {
    private Map<Integer, Producto> listaProductos;  
    private String compania, link;
    // Constructr
    public Proveedor(String nom, String ni, String ape, String cor, Date naci, String comp, String lin) {
        super(nom, ni, ape, cor, naci);
        this.compania = comp;
        this.link = lin;
        this.listaProductos = new HashMap<>();
    }
    // Gets sets
    public String getCompania() {
        return compania;
    }

    public void setCompania(String comp) {
        this.compania = comp;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String lin) {
        this.link = lin;
    }

    public Map<Integer, Producto> getProductos() {
        return listaProductos;//??????'
    }
    public void agregarProd(int id, Producto prod) {
        listaProductos.put(id, prod);
    }
    public Producto obtenerProd(int id) {
        return listaProductos.get(id);
    }
    public void eliminarProd(int id) {
        listaProductos.remove(id);
    }
    public boolean existeProd(int id) {
        return listaProductos.containsKey(id);
    }
    public int cantProd() {
        return listaProductos.size();
    }

}
