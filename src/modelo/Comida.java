package modelo;

public class Comida {
    private String producto;
    private int precio;
    private int unidades;

    public Comida(String producto, int precio, int unidades) {
        this.producto = producto;
        this.precio = precio;
        this.unidades = unidades;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public String toString() {
        return producto;
    }
}
