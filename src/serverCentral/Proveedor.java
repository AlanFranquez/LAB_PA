package serverCentral;

public class Proveedor extends Usuario {
    private List<Producto> listaProductos;  
    private String compania, link;
    // Constructr
    public Proveedor(String nom, String ni, String ape, String cor, Date naci, String comp, String lin) {
        super(nom, ni, ape, cor, naci);
        this.compania = comp;
        this.link = lin;
        this.listaProductos = new ArrayList<>();
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

    public List<Producto> getProductos() {
        return listaProductos;
    }

  
