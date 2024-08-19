package serverCentral;

public class Item {
    private int cant;
    private float subTotal;
    private Map<int, Producto> productos;

  public Item(int cant) {
        this.cant = cant;
        this.productos = new HashMap<>();
        this.subTotal = 0.0;
    }
    //getts y setts
    public int getCant() {
        return cant;
    }
    public void setCant(int cant) {
        this.cant = cant;
        updateSubTotal(); // Actualizar cuando cambie la cantidad
    }
    public float getSubTotal() {
        return subTotal;
    }
    public Map<int, Producto> getProductos() {
        return productos;
    }
    public void setProductos(Map<int, Producto> productos) {
        this.productos = productos;
    }

    public void addProducto(int cod, Producto producto) {
        productos.put(cod, producto);
        updateSubTotal(); // Actualizar cuando se añada un producto
    }
    // Método para actualizar el subtotal
    private void updateSubTotal() {
        subTotal = 0.0;
        for (Producto producto : productos.values())
            subTotal += producto.getPrecio() * cant;
    }
}
