import modelo.Ciudad;
import modelo.Comida;
import BBDD.Controller;
import vista.Consola;

import java.util.ArrayList;
import java.util.List;


public class Main {

    static Consola consola;
    static Controller controller;
    static List<Comida> pedidosUsuario, productosComida;
    static List<Ciudad> listaCiudad;
    static int id , unidades_final;

    public static void mostrarCostePedido() {

        for (int i = 0; i < pedidosUsuario.size() ; i++) {
            System.out.println(pedidosUsuario.get(i) + " x" +pedidosUsuario.get(i).getUnidades() + " " + (pedidosUsuario.get(i).getPrecio()*pedidosUsuario.get(i).getUnidades()) + "€");
        }
        System.out.println();

        int precio_final = pedidosUsuario.stream()
                .mapToInt(pedidosUsuario_-> pedidosUsuario_.getPrecio() * pedidosUsuario_.getUnidades())
                .sum();
        System.out.println("El coste de su pedido es de " + precio_final+"€");
    }

    public static void mostrarCiudades() {
        listaCiudad = controller.ciudadesBBDD();
        System.out.println("A qué ciudad quieres que te llevemos la comida?");
        Ciudad c = consola.elegirCiudad(listaCiudad);
        System.out.println("Perfecto! Enviaremos tu pedido a " + c.getCiudad() + " en aproximadamente " + c.getTiempo() + " minutos.");
        System.out.println("Gracias por su compra!");
    }

    public static void main(String[] args) {
        controller = Controller.getInstance();
        consola = Consola.getInstance();

        pedidosUsuario = new ArrayList<>();
        productosComida = controller.listaProductosBBDD();
        pedidosUsuario = consola.elegirPedido(productosComida);
        Comida.actualizarUnidadesBD(pedidosUsuario,productosComida);
        mostrarCiudades();

        mostrarCostePedido();
    }
}
