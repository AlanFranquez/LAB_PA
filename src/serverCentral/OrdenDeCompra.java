package serverCentral;

import java.util.HashMap;
import java.util.Map;

public class OrdenDeCompra {
    private int numero;
    private DTFecha fecha;
    private float precioTotal;
    private Map<Integer, Item> items;

    public OrdenDeCompra(int numero, DTFecha fecha) {
        this.numero = numero;
        this.fecha = fecha;
        this.precioTotal = 0;
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
    public Map<Integer, Item> getItems() {
        return items;
    }
    public void setPrecioTotal(Map<Integer, Item> mapItems) {
        float total = 0;
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
