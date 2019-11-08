package modelo;

import BBDD.Controller;

import java.util.ArrayList;
import java.util.List;

public class Comida {
    private int id;
    private String producto;
    private int precio;
    private int unidades;

    public Comida(){};

    public Comida(int id ,String producto, int precio, int unidades) {
        this.id = id;
        this.producto = producto;
        this.precio = precio;
        this.unidades = unidades;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

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

    public static void actualizarUnidadesBD(List<Comida> pedidosUsuario , List<Comida> productosComida){

        Controller controller = Controller.getInstance();

        int id , unidades_final;


        for (int i = 0; i < pedidosUsuario.size(); i++) {
            id = pedidosUsuario.get(i).getId();
            unidades_final = productosComida.get(id - 1).getUnidades() - pedidosUsuario.get(i).getUnidades();

            controller.actualizarUnidadesBD(id , unidades_final);

        }

    }
}
