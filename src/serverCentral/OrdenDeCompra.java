package serverCentral;

public class OrdenCompra {
    private int numero;
    private DTFecha fecha;
    private float precioTotal;
    private Map<int, Item> MapItems;

    public OrdenCompra(int numero, DTFecha fecha, float precioTotal) {
        this.numero = numero;
        this.fecha = fecha;
        this.precioTotal = 0.0;
        this.items = new HashMap<>();
    }
    //gets setts
    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
    public DTFecha getFecha() {
        return fecha;
    }
    public void setFecha(DTFecha fecha) {
        this.fecha = fecha;
    }
    public float getPrecioTotal() {
        return precioTotal;
    }
    public Map<int, Item> getItems() {
        return items;
    }
    public void setPrecioTotal(Map<Int, Item> mapItems) {
        float total = 0.0;
        for (Item item : mapItems.values()) {
            total += item.getSubTotal();
        }
        this.precioTotal = total;
    }

    //añadir ítem
    /*public void addItem(Item item) {
        
    }*/
    //eliminar ítem
    /*public void removeItem(int codigo) {
        
    }*/
}
